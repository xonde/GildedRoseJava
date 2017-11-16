package training.gildedrose;

import java.util.HashMap;
import java.util.Map;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private static final Map<String, QualityRule> QUALITY_RULES = new HashMap<>();

    static {
        QUALITY_RULES.put(AGED_BRIE, item -> item.quality + (isExpired(item) ? 2 : 1));
        QUALITY_RULES.put(BACKSTAGE_PASS, GildedRose::newBackstagePassQuality);
        QUALITY_RULES.put(SULFURAS, item -> item.quality);
    }

    private static final QualityRule DEFAULT_QUALITY_RULE = item -> item.quality - (isExpired(item) ? 2 : 1);

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    public void updateQuality() {
        for (Item item : items) {
            if (!isSulfuras(item)) {
                item.sellIn = item.sellIn - 1;
            }

            QualityRule rule = QUALITY_RULES.getOrDefault(item.name, DEFAULT_QUALITY_RULE);
            item.quality = rule.newQuality(item);

            enforceQualityRange(item);
        }
    }

    private void enforceQualityRange(Item item) {
        if (isSulfuras(item)) {
            return;
        }

        if (item.quality < 0) {
            item.quality = 0;
        }
        if (item.quality > 50) {
            item.quality = 50;
        }
    }

    private static int newBackstagePassQuality(Item pass) {
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

    private static boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    private static boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS);
    }

    private interface QualityRule {
        int newQuality(Item item);
    }
}
