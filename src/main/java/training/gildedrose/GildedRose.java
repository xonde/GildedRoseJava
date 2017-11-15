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
                item.quality += isExpired(item) ? 2 : 1;
            } else if (isBackstagePass(item)) {
                item.quality = newBackstagePassQuality(item);
            } else {
                item.quality -= isExpired(item) ? 2 : 1;
            }

            enforceQualityRange(item);
        }
    }

    private void enforceQualityRange(Item item) {
        if (item.quality < 0) {
            item.quality = 0;
        }
        if (item.quality > 50) {
            item.quality = 50;
        }
    }

    private int newBackstagePassQuality(Item pass) {
        if (isExpired(pass)) {
            return 0;
        }

        if (pass.sellIn < 5) {
            return pass.quality + 3;
        }

        if (pass.sellIn < 10) {
            return pass.quality + 2;
        }

        return pass.quality + 1;
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
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
