package in.gov.rera.citizen.survey.model.transaction;

import java.util.List;

public class BlockDetailTransactionModel {

	private Long blockId;
	private String blockName;
	private String blockDescripton;
	private String blockPhoto;
	private String blockHeight;
	private List<FlatModelTransactionModel> flatList ;
	
	public Long getBlockId() {
		return blockId;
	}
	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getBlockDescripton() {
		return blockDescripton;
	}
	public void setBlockDescripton(String blockDescripton) {
		this.blockDescripton = blockDescripton;
	}
	public String getBlockPhoto() {
		return blockPhoto;
	}
	public void setBlockPhoto(String blockPhoto) {
		this.blockPhoto = blockPhoto;
	}
	public String getBlockHeight() {
		return blockHeight;
	}
	public void setBlockHeight(String blockHeight) {
		this.blockHeight = blockHeight;
	}
	public List<FlatModelTransactionModel> getFlatList() {
		return flatList;
	}
	public void setFlatList(List<FlatModelTransactionModel> flatList) {
		this.flatList = flatList;
	}
	
	
	
	}
