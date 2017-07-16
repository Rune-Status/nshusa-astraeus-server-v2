package io.astraeus.cache;

import java.io.IOException;
import java.util.logging.Logger;

import com.softgate.fs.IndexedFileSystem;
import com.softgate.fs.binary.Archive;

import io.astraeus.net.codec.ByteBufUtils;
import io.astraeus.util.LoggerUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.val;

@Data
public final class NpcDefinition {
	
	private static final Logger logger = LoggerUtils.getLogger(NpcDefinition.class);
	
	private static NpcDefinition[] definitions;
	
	private static int npcCount;

	public int turn90CCWAnimIndex = -1;
	public int varbitId = -1;
	public int turn180AnimIndex = -1;
	public int settingId = -1;
	public int combatLevel = -1;
	public String name = "Dwarf Remains";
	public String actions[];
	public int walkingAnimation = -1;	
	public byte tilesOccupied = 1;	
	public int[] recolourTarget;
	public int[] aditionalModels;
	public int headIcon = -1;
	public int[] recolourOriginal;
	public int stanceAnimation = -1;	
	public long interfaceType = -1L;
	public int degreesToTurn = 32;
	public int turn90CWAnimIndex = -1;
	public boolean clickable = true;
	public int ambient;
	public int resizeY = 128;	
	public boolean drawMinimapDot = true;
	public int childIds[];
	public int resizeX = 128;	
	public int contrast;
	public boolean priorityRender;
	public int[] models;	
	public int id;
	
	public NpcDefinition(int id) {
		this.id = id;
	}
	
	public static NpcDefinition lookup(int id) {
		NpcDefinition def = definitions[id];
		
		return def == null ? definitions[0] : def;
	}
	
	public static int getCount() {
		return npcCount;
	}
	
	public static void decode(IndexedFileSystem fs) {
		try {

			val store = fs.getStore(0);

			Archive archive = Archive.decode(store.readFile(2));

			val datBuf = Unpooled.wrappedBuffer(archive.readFile("npc.dat"));

			val size = datBuf.readUnsignedShort();
			
			npcCount = size;
			
			val defs = new NpcDefinition[size];
			
			for (int i = 0; i < defs.length; i++) {
				
				defs[i] = decode(i, datBuf);
			}
			
			NpcDefinition.definitions = defs;

			logger.info(String.format("Loaded: %d npc definitions.", size));			

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private static NpcDefinition decode(int id, ByteBuf buf) {		
		
		val def = new NpcDefinition(id);
		
		while(true) {
			
			val opcode = buf.readUnsignedByte();
			
			if (opcode == 0) {
				return def;
			} else if (opcode == 1) {
				val length = buf.readUnsignedByte();				
				def.setModels(new int[length]);
				for (int i = 0; i < length; i++) {					
					def.getModels()[i] = buf.readUnsignedShort();
				}

			} else if (opcode == 2) {
				def.setName(ByteBufUtils.readJagString(buf));
			} else if (opcode == 12) {
				def.setTilesOccupied(buf.readByte());
			} else if (opcode == 13) {
				def.setStanceAnimation(buf.readUnsignedShort());
			} else if (opcode == 14) {
				def.setWalkingAnimation(buf.readUnsignedShort());
			} else if (opcode == 17) {
				def.setWalkingAnimation(buf.readUnsignedShort());
				def.setTurn180AnimIndex(buf.readUnsignedShort());
				def.setTurn90CWAnimIndex(buf.readUnsignedShort());
				def.setTurn90CCWAnimIndex(buf.readUnsignedShort());
			} else if (opcode >= 30 && opcode < 40) {
				if (def.getActions() == null) {
					def.setActions(new String[5]);
				}
				
				def.getActions()[opcode - 30] = ByteBufUtils.readJagString(buf);
				if (def.getActions()[opcode - 30].equalsIgnoreCase("hidden")) {
					def.getActions()[opcode - 30] = null;
				}
			} else if (opcode == 40) {
				val colors = buf.readUnsignedByte();				
				def.setRecolourOriginal(new int[colors]);
				def.setRecolourTarget(new int[colors]);
				for (int i = 0; i < colors; i++) {					
					def.getRecolourOriginal()[i] = buf.readUnsignedShort();
					def.getRecolourTarget()[i] = buf.readUnsignedShort();
				}

			} else if (opcode == 60) {
				val length = buf.readUnsignedByte();			
				def.setAditionalModels(new int[length]);
				for (int i = 0; i < length; i++) {
					def.getAditionalModels()[i] = buf.readUnsignedShort();
				}

			} else if (opcode == 90) {
				buf.readUnsignedShort();
			} else if (opcode == 91) {
				buf.readUnsignedShort();
			} else if (opcode == 92) {
				buf.readUnsignedShort();
			} else if (opcode == 93) {
				def.setDrawMinimapDot(false);
			} else if (opcode == 95) {
				def.setCombatLevel(buf.readUnsignedShort());
			} else if (opcode == 97) {
				def.setResizeX(buf.readUnsignedShort());
			} else if (opcode == 98) {
				def.setResizeY(buf.readUnsignedShort());
			} else if (opcode == 99) {
				def.setPriorityRender(true);				
			} else if (opcode == 100) {
				def.setAmbient(buf.readByte());
			} else if (opcode == 101) {
				def.setContrast(buf.readByte() * 5);
			} else if (opcode == 102) {
				// could be readUnsignedByte
				def.setHeadIcon(buf.readUnsignedShort());
			} else if (opcode == 103) {
				// could be readUnsignedShort				
				def.setDegreesToTurn(buf.readUnsignedShort());
			} else if (opcode == 106) {
				def.setVarbitId(buf.readUnsignedShort());
				if (def.getVarbitId() == 65535) {
					def.setVarbitId(-1);
				}
				def.setSettingId(buf.readUnsignedShort());
				if (def.getSettingId() == 65535) {
					def.setSettingId(-1);
				}
				val childCount = buf.readUnsignedByte();
				def.setChildIds(new int[childCount + 1]);
				for (int i = 0; i <= childCount; i++) {					
					def.getChildIds()[i] = buf.readUnsignedShort();
					if (def.getChildIds()[i] == 65535) {
						def.getChildIds()[i] = -1;
					}
				}

			} else if (opcode == 107) {
				def.setClickable(false);
			}
		}
	

	}
	
}
