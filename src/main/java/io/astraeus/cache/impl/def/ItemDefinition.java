package io.astraeus.cache.impl.def;

import java.io.IOException;
import java.util.logging.Logger;

import com.softgate.fs.FileStore;
import com.softgate.fs.IndexedFileSystem;
import com.softgate.fs.binary.Archive;

import io.astraeus.game.world.entity.item.Item;
import io.astraeus.net.codec.ByteBufUtils;
import io.astraeus.util.LoggerUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public final class ItemDefinition {
	
	private static final Logger logger = LoggerUtils.getLogger(ItemDefinition.class);
	
	@Getter @Setter
	private static ItemDefinition[] definitions;
	
	private byte equippedFemaleModelTranslationY;
	private int value = 1;
	private int[] modifiedModelColors;
	private int id = -1;
	private int[] originalModelColors;
	private boolean members;
	private int equippedFemaleModel3 = -1;
	private int unnotedId = -1;	
	private int equippedFemaleModel2 = -1;
	private int equippedMaleModel1 = -1;
	private int equippedMaleModelDialogue2 = -1;
	private int modelScaleX = 128;
	private String groundActions[];
	private int translateX;
	private String name = "Dwarf Remains";
	private int equippedFemaleModelDialogue2 = -1;
	private int inventoryModel;
	private int equippedMaleModelDialogue1 = -1;
	private boolean stackable;
	private String description;
	private int notedId = -1;	
	private int modelZoom = 2000;
	private int lightMag;
	private int equippedMaleModel3 = -1;
	private int equippedMaleModel2 = -1;
	private String actions[];
	private int rotationY;
	private int modelScaleZ = 128;
	private int modelScaleY = 128;
	private int[] stackVariantId;
	private int translateYZ;//
	private int lightIntensity;
	private int equippedFemaleModelDialogue1 = -1;
	private int rotationX;
	private int equippedFemaleModel1 = -1;
	private int[] stackVariantSize;
	private int team;
	private int rotationZ;	
	private byte equippedMaleModelTranslationY;

	protected ItemDefinition(int id) {
		this.id = id;
	}	
	
	public static ItemDefinition lookup(int id) {
		ItemDefinition def = definitions[id];
		return def == null ? definitions[0] : def;
	}
	
	public boolean isNoted() {
		return notedId ==  (id - 1) && unnotedId == Item.NOTED_ITEM;
	}
	
	public boolean isNoteable() {
		return notedId == (id + 1) && isUnnoted();
	}
	
	public boolean isUnnoted() {
		return unnotedId == -1;
	}
	
	public boolean isStackable() {
		return stackable || isNoted();
	}
	
	public static void decode(IndexedFileSystem fs) {
		
		FileStore store = fs.getStore(0);
		
		try {
			Archive archive = Archive.decode(store.readFile(2));
			
			ByteBuf dataBuf = Unpooled.wrappedBuffer(archive.readFile("obj.dat"));
			ByteBuf idxBuf = Unpooled.wrappedBuffer(archive.readFile("obj.idx"));
			
			int size = idxBuf.readUnsignedShort();
			
			ItemDefinition[] defs = new ItemDefinition[size];
			
			dataBuf.skipBytes(2);
			
			for (int i = 0; i < size; i++) {				
				defs[i] = decode(i, dataBuf);				
			}
			
			ItemDefinition.setDefinitions(defs);
			
			logger.info("Loaded: " + ItemDefinition.getDefinitions().length + " item definitions.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static ItemDefinition decode(int id, ByteBuf buf) {
		ItemDefinition def = new ItemDefinition(id);
		while(true) {
			int opcode = buf.readUnsignedByte();
			
			if (opcode == 0) {
				return def;
			} else if (opcode == 1) {
				def.setInventoryModel(buf.readUnsignedShort());
			} else if (opcode == 2) {
				def.setName(ByteBufUtils.readJagString(buf));
			} else if (opcode == 3) {
				def.setDescription(ByteBufUtils.readJagString(buf));
			} else if (opcode == 4) {
				def.setModelZoom(buf.readUnsignedShort());	
			} else if (opcode == 5) {
				def.setRotationY(buf.readUnsignedShort());	
			} else if (opcode == 6) {
				def.setRotationX(buf.readUnsignedShort());
			} else if (opcode == 7) {
				def.setTranslateX(buf.readUnsignedShort());				
				if (def.getTranslateX() > 32767)
					def.setTranslateX(def.getTranslateX() - 0x10000);
			} else if (opcode == 8) {
				def.setTranslateYZ(buf.readUnsignedShort());				
				if (def.getTranslateYZ() > 32767)
					def.setTranslateYZ(def.getTranslateYZ() - 0x10000);
			} else if (opcode == 10) {
				buf.readUnsignedShort();
			} else if (opcode == 11) {
				def.setStackable(true);
			} else if (opcode == 12) {
				def.setValue(buf.readInt());
			} else if (opcode == 16) {
				def.setMembers(true);
			} else if (opcode == 23) {
				def.setEquippedMaleModel1(buf.readUnsignedShort());				
				def.setEquippedMaleModelTranslationY(buf.readByte());
			} else if (opcode == 24) {			
				def.setEquippedMaleModel2(buf.readUnsignedShort());
			} else if (opcode == 25) {				
				def.setEquippedFemaleModel1(buf.readUnsignedShort());				
				def.setEquippedFemaleModelTranslationY(buf.readByte());
			} else if (opcode == 26) {			
				def.setEquippedFemaleModel2(buf.readUnsignedShort());
			} else if (opcode >= 30 && opcode < 35) {
				if (def.getGroundActions() == null) {
					def.setGroundActions(new String[5]);
				}				
				def.getGroundActions()[opcode - 30] = ByteBufUtils.readJagString(buf);				
				if (def.getGroundActions()[opcode - 30].equalsIgnoreCase("hidden")) {
					def.getGroundActions()[opcode - 30] = null;
				}				
			} else if (opcode >= 35 && opcode < 40) {
				if (def.getActions() == null) {
					def.setActions(new String[5]);
				}
				def.getActions()[opcode - 35] = ByteBufUtils.readJagString(buf);
			} else if (opcode == 40) {
				
				int colors = buf.readUnsignedByte();				
				def.setModifiedModelColors(new int[colors]);				
				def.setOriginalModelColors(new int[colors]);				
				for (int i = 0; i < colors; i++) {						
					def.getModifiedModelColors()[i] = buf.readUnsignedShort();
					def.getOriginalModelColors()[i] = buf.readUnsignedShort();
				}
			} else if (opcode == 78) {
				def.setEquippedMaleModel3(buf.readUnsignedShort());	
			} else if (opcode == 79) {
				def.setEquippedFemaleModel3(buf.readUnsignedShort());	
			} else if (opcode == 90) {
				def.setEquippedMaleModelDialogue1(buf.readUnsignedShort());
			} else if (opcode == 91) {
				def.setEquippedFemaleModelDialogue1(buf.readUnsignedShort());	
			} else if (opcode == 92) {
				def.setEquippedMaleModelDialogue2(buf.readUnsignedShort());	
			} else if (opcode == 93) {
				def.setEquippedFemaleModelDialogue2(buf.readUnsignedShort());	
			} else if (opcode == 95) {
				def.setRotationZ(buf.readUnsignedShort());
			} else if (opcode == 97) {
				def.setNotedId(buf.readUnsignedShort());
			} else if (opcode == 98) {
				def.setUnnotedId(buf.readUnsignedShort());
			} else if (opcode >= 100 && opcode < 110) {
				if (def.getStackVariantId() == null) {					
					def.setStackVariantId(new int[10]);
					def.setStackVariantSize(new int[10]);					
				}
				def.getStackVariantId()[opcode - 100] = buf.readUnsignedShort();
				def.getStackVariantSize()[opcode - 100] = buf.readUnsignedShort();
			} else if (opcode == 110) {
				def.setModelScaleX(buf.readUnsignedShort());	
			} else if (opcode == 111) {
				def.setModelScaleY(buf.readUnsignedShort());
			} else if (opcode == 112) {
				def.setModelScaleZ(buf.readUnsignedShort());
			} else if (opcode == 113) {
				def.setLightIntensity(buf.readByte());	
			} else if (opcode == 114) {
				def.setLightMag(buf.readByte() * 5);
			} else if (opcode == 115) {
				def.setTeam(buf.readUnsignedByte());
			}
		}
	}

}
