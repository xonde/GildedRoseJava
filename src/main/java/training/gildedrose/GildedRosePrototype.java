package training.gildedrose;


public class GildedRosePrototype {
    private Item[] items;
    private int MAX_QUALITY = 50; //sulfuras is the exception stays at 80
    private int MIN_QUALITY = 0;
    private int MAX_SULFURAS_QUALITY = 80;

    public GildedRosePrototype(Item[] items) {
        this.items = items;
    }

    private void updateAgedBrieQuality(Item item){
        int multiplier = 1;
        if(item.sellIn < 0){
            multiplier = 2;
        }
        if(item.quality < MAX_QUALITY){
            item.quality += multiplier;
        }
    }

    private void updateBackstagePassQuality(Item item){
        int multiplier = 1;
        if(item.sellIn < 10){
            multiplier = 2;
        }
        if(item.sellIn < 5){
            multiplier = 3;
        }
        if(item.quality < MAX_QUALITY){
            item.quality += multiplier;
        }
        if(item.sellIn < 0){
            item.quality = MIN_QUALITY;
        }
    }

    private void updateNormalItemQuality(Item item){
        int multiplier = 1;
        if(item.sellIn < 0){
            multiplier = 2;
        }
        if(item.quality > MIN_QUALITY){
            item.quality -= multiplier;
        }
    }

    private void updateConjuredItemQuality(Item item){
        int multiplier = 1;
        if(item.sellIn < 0){
            multiplier = 2;
        }
        if(item.quality > MIN_QUALITY){
            item.quality -= 2 * multiplier;
        }
    }

    private void updateSulfurasQuality(Item item){
        item.quality = MAX_SULFURAS_QUALITY;
    }

    private void updateSellin(Item item){
        if(!item.name.equals("Sulfuras, Hand of Ragnaros")){
            item.sellIn -= 1;
        }
    }

    public void updateQuality(){
        for(Item item: this.items){
            updateSellin(item);

            if(item.name.equals("Aged Brie")){
                updateAgedBrieQuality(item);
            }
            else if(item.name.equals("Backstage passes to a TAFKAL80ETC concert")){
                updateBackstagePassQuality(item);
            }
            else if(item.name.contains("Conjured")){
                updateConjuredItemQuality(item);
            }
            else if(item.name.equals("Sulfuras, Hand of Ragnaros")){
                updateSulfurasQuality(item);
            }

            else{
                updateNormalItemQuality(item);
            }

            System.out.println(item.toString());
        }
    }


}
