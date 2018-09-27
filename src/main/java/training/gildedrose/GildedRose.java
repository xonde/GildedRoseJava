package training.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        //Loop through all items
        for (int i = 0; i < items.length; i++) {
            //if the item is not aged brie......and is not a backstage pass...
            if (!items[i].name.equals("Aged Brie") && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {

                //...and the quality is greater than 0...(quality cannot go down past 0)
                if (items[i].quality > 0) {

                    //...and isn't legendary item sulfuras...
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {

                        //Then it is a normal item so take the quality down by 1
                        items[i].quality = items[i].quality - 1;
                    }
                }
            }

            //..the item is aged brie or a backstage pass
            else {

                //if the item has a quality lower than 50(quality cannot go past 50)...
                if (items[i].quality < 50) {

                    //Then take the value up by ONE initially
                    items[i].quality = items[i].quality + 1;

                    //...if it's a backstage pass...
                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {

                        //...and has 10 days or less left to sell it
                        if (items[i].sellIn < 11) {

                            //...and has a quality lower than 50(quality cannot go past 50)...
                            if (items[i].quality < 50) {

                                //...take quality up by an additional ONE, making it +2
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        //Now check if backstage pass has 5 days or less left to sell
                        if (items[i].sellIn < 6) {

                            //...and has a quality lower than 50(quality cannot go past 50)...
                            if (items[i].quality < 50) {

                                //...take quality up by an additional ONE, making it +3
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            //Update the item's sell in date, unless it sulfuras, which doesn't have a sell in
            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            //If the item is past it's sell in date..
            if (items[i].sellIn < 0) {

                //...and isn't aged brie
                if (!items[i].name.equals("Aged Brie")) {

                    //...and isn't a backstage pass
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {

                        //...and the quality is greater than 0...(quality cannot go down past 0)
                        if (items[i].quality > 0) {

                            //...and isn't sulfuras
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {

                                //Then it is a normal item so take it's quality down by 1
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    }

                    //Then it is a backstage pass
                    else {

                        //Set it's quality to ZERO, concert already happened
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                }

                //Then it's aged brie
                else {

                    //...and has a quality lower than 50(quality cannot go past 50)...
                    if (items[i].quality < 50) {

                        //Then take up quality by ONE, aged brie get's better with time
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}