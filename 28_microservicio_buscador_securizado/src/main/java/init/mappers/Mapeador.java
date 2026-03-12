package init.mappers;

import org.springframework.stereotype.Component;

import init.dtos.ItemDto;
import init.model.Item;

@Component
public class Mapeador {
	
	public ItemDto itemEntitytoDto(Item item) {
		return new ItemDto(item.getUrl(), item.getTematica(), item.getDescripcion());
	}
	
	public Item itemDtoToEntity(ItemDto item)
	{
		return new Item(item.getUrl(), item.getTematica(), item.getDescripcion());
	}

}
