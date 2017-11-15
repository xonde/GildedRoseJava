package training.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    public void updateQuality() {
        for (Item item : items) {
            if (isSulfuras(item)) {
                continue;
            }

            item.sellIn = item.sellIn - 1;

            if (isAgedBrie(item)) {
                updateBrieQuality(item);
            } else if (isBackstagePass(item)) {
                updateBackstagePassQuality(item);
            } else {
                updateStandardItemQuality(item);
            }


        }
    }

    private void updateStandardItemQuality(Item item) {
        decreaseQuality(item);
        if (isExpired(item)) {
            decreaseQuality(item);
        }
    }

    private void updateBackstagePassQuality(Item pass) {
        if (isExpired(pass)) {
            pass.quality = 0;
        } else {
            increaseQuality(pass);

            if (pass.sellIn < 10) {
                increaseQuality(pass);
            }

            if (pass.sellIn < 5) {
                increaseQuality(pass);
            }
        }
    }

    private void updateBrieQuality(Item brie) {
        increaseQuality(brie);

        if (isExpired(brie)) {
            increaseQuality(brie);
        }
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private static boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private static boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }
}
