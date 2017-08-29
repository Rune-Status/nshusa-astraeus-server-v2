package io.astraeus.cache.impl.def;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.softgate.fs.FileStore;
import com.softgate.fs.IndexedFileSystem;
import com.softgate.fs.binary.Archive;

import io.astraeus.net.codec.ByteBufUtils;
import io.astraeus.util.LoggerUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public final class ObjectDefinition {
	
	private static final Logger logger = LoggerUtils.getLogger(ObjectDefinition.class);
	
	@Getter @Setter
	private static ObjectDefinition[] definitions;

	private boolean obstructsGround;
	private byte ambientLighting;
	private int translateX;
	private String name;
	private int scaleZ = 128;
	private byte lightDiffusion;
	private int objectSizeX = 1;
	private int translateY;
	private int minimapFunction = -1;
	private int[] originalModelColors;
	private int scaleX = 128;
	private int varp = -1;
	private boolean inverted;
	private int id = -1;
	private boolean impenetrable = true;
	private int mapscene = -1;
	private int childrenIDs[];
	private int supportItems = -1;
	private int objectSizeY = 1;
	private boolean contouredGround;
	private boolean occludes;
	private boolean hollow;
	private boolean solid = true;
	private int surroundings;
	private boolean delayShading;
	private int scaleY = 128;
	private int[] modelIds;
	private int varbit = -1;
	private int decorDisplacement = 16;
	private int[] modelTypes;
	private String description;
	private boolean isInteractive;
	private boolean castsShadow = true;
	private int animation = -1;
	private int translateZ;
	private int[] modifiedModelColors;
	private String interactions[];

	private short[] originalTexture;
	private short[] modifiedTexture;

	public ObjectDefinition(int id) {
		this.id = id;		
	}
	
	public static void decode(IndexedFileSystem fs) {		
		try {
		
		FileStore store = fs.getStore(0);

		Archive archive = Archive.decode(store.readFile(2));
		
		ByteBuf datBuf = Unpooled.wrappedBuffer(archive.readFile("loc.dat"));
		ByteBuf idxBuf = Unpooled.wrappedBuffer(archive.readFile("loc.idx"));

		final int count = idxBuf.readUnsignedShort();

		datBuf.skipBytes(2);
		
		final ObjectDefinition[] defs = new ObjectDefinition[count];
		
		for (int i = 0; i < defs.length; i++) {
			defs[i] = decode(i, datBuf);
		}
		
		ObjectDefinition.setDefinitions(defs);		

		logger.info(String.format("Loaded: %d object definitions.", count));
		
		} catch (IOException ex) {			
			logger.log(Level.SEVERE, "Failed to load object definitions.");
			ex.printStackTrace();
		}
	}
	
	private static ObjectDefinition decode(int id, ByteBuf buf) {		
		
		final ObjectDefinition def = new ObjectDefinition(id);
		
		int flag = -1;
		
		while(true) {
			int opcode = buf.readUnsignedByte();
			
			if (opcode == 0) {
				break;
			}
			
			if (opcode == 1) {
				int len = buf.readUnsignedByte();
				if (len > 0) {
					if (def.getModelIds() == null) {
						def.setModelTypes(new int[len]);
						def.setModelIds(new int[len]);
						for (int k1 = 0; k1 < len; k1++) {
							def.getModelIds()[k1] = buf.readUnsignedShort();
							def.getModelTypes()[k1] = buf.readUnsignedByte();
						}
					} else {
						buf.skipBytes(buf.readerIndex() + len * 3);
					}
				}
			} else if (opcode == 2) {
				def.setName(ByteBufUtils.readJagString(buf));
			} else if (opcode == 3) {
				def.setDescription(ByteBufUtils.readJagString(buf));
			} else if (opcode == 5) {
				int len = buf.readUnsignedByte();
				if (len > 0) {
					if (def.getModelIds() == null) {
						def.setModelTypes(null);
						def.setModelIds(new int[len]);
						for (int l1 = 0; l1 < len; l1++)
							def.getModelIds()[l1] = buf.readUnsignedShort();
					} else {
						buf.skipBytes(buf.readerIndex() + len * 2);
					}
				}
			} else if (opcode == 14)
				def.setObjectSizeX(buf.readUnsignedByte());
			else if (opcode == 15)
				def.setObjectSizeY(buf.readUnsignedByte());
			else if (opcode == 17)
				def.setSolid(false);
			else if (opcode == 18)
				def.setImpenetrable(false);
			else if (opcode == 19)
				def.setInteractive(buf.readUnsignedByte() == 1);
			else if (opcode == 21)
				def.setContouredGround(true);
			else if (opcode == 22)
				def.setDelayShading(false);
			else if (opcode == 23)
				def.setOccludes(true);
			else if (opcode == 24) {
				def.setAnimation(buf.readUnsignedShort());
				if (def.getAnimation() == 65535)
					def.setAnimation(-1);
			} else if (opcode == 28)
				def.setDecorDisplacement(buf.readUnsignedByte());
			else if (opcode == 29)
				def.setAmbientLighting(buf.readByte());
			else if (opcode == 39)
				def.setLightDiffusion(buf.readByte());
			else if (opcode >= 30 && opcode < 39) {
				if (def.getInteractions() == null)
					def.setInteractions(new String[5]);
				def.getInteractions()[opcode - 30] = ByteBufUtils.readJagString(buf);
				if (def.getInteractions()[opcode - 30].equalsIgnoreCase("hidden"))
					def.getInteractions()[opcode - 30] = null;
			} else if (opcode == 40) {
				int i1 = buf.readUnsignedByte();
				def.setModifiedModelColors(new int[i1]);
				def.setOriginalModelColors(new int[i1]);
				for (int i2 = 0; i2 < i1; i2++) {
					def.getModifiedModelColors()[i2] = buf.readUnsignedShort();
					def.getOriginalModelColors()[i2] = buf.readUnsignedShort();
				}
			} else if (opcode == 41) {
				int j2 = buf.readUnsignedByte();
				def.setModifiedTexture(new short[j2]);
				def.setOriginalTexture(new short[j2]);
				for (int k = 0; k < j2; k++) {
					def.getModifiedTexture()[k] = (short) buf.readUnsignedShort();
					def.getOriginalTexture()[k] = (short) buf.readUnsignedShort();
				}

			} else if (opcode == 82)
				def.setMinimapFunction(buf.readUnsignedShort());
			else if (opcode == 62)
				def.setInverted(true);
			else if (opcode == 64)
				def.setCastsShadow(false);
			else if (opcode == 65)
				def.setScaleX(buf.readUnsignedShort());
			else if (opcode == 66)
				def.setScaleY(buf.readUnsignedShort());
			else if (opcode == 67)
				def.setScaleZ(buf.readUnsignedShort());
			else if (opcode == 68)
				def.setMapscene(buf.readUnsignedShort());
			else if (opcode == 69)
				def.setSurroundings(buf.readUnsignedByte());
			else if (opcode == 70)
				def.setTranslateX(buf.readShort());
			else if (opcode == 71)
				def.setTranslateY(buf.readShort());
			else if (opcode == 72)
				def.setTranslateZ(buf.readShort());
			else if (opcode == 73)
				def.setObstructsGround(true);
			else if (opcode == 74)
				def.setHollow(true);
			else if (opcode == 75)
				def.setSupportItems(buf.readUnsignedByte());
			else if (opcode == 77) {
				def.setVarp(buf.readUnsignedShort());
				if (def.getVarp() == 65535) {
					def.setVarp(-1);
				}
				def.setVarbit(buf.readUnsignedShort());
				if (def.getVarbit() == 65535) {
					def.setVarbit(-1);
				}
				int length = buf.readUnsignedByte();				
				def.setChildrenIDs(new int[length]);
				for (int i = 0; i <= length; i++) {					
					def.getChildrenIDs()[i] = buf.readUnsignedShort();
					if (def.getChildrenIDs()[i] == 65535)
						def.getChildrenIDs()[i] = -1;
				}
			}
		}

		if (flag == -1 && def.getName() != "null" && def.getName() != null) {
			def.setInteractive(def.getModelIds() != null && (def.getModelTypes() == null || def.getModelTypes()[0] == 10));
			
			if (def.getInteractions() != null) {
				def.setInteractive(true);
			}
		}
		if (def.isHollow()) {
			def.setSolid(false);
			def.setImpenetrable(false);
		}
		
		if (def.getSupportItems() == -1) {
			def.setSupportItems(def.isSolid() ? 1 : 0);
		}
		
		return def;
		
	}

}
