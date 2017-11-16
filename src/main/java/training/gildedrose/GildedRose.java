package training.gildedrose;

import java.util.HashMap;
import java.util.Map;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private static final Map<String, QualityRule> NAMED_ITEM_QUALITY_RULES = new HashMap<>();

    static {
        NAMED_ITEM_QUALITY_RULES.put(AGED_BRIE, item -> item.quality + (isExpired(item) ? 2 : 1));
        NAMED_ITEM_QUALITY_RULES.put(BACKSTAGE_PASS, GildedRose::newBackstagePassQuality);
        NAMED_ITEM_QUALITY_RULES.put(SULFURAS, item -> item.quality);
    }

    private static final QualityRule STANDARD_QUALITY_RULE = item -> item.quality - (isExpired(item) ? 2 : 1);
    private static final QualityRule CONJURED_QUALITY_RULE = item -> item.quality - (isExpired(item) ? 4 : 2);

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    public void updateQuality() {
        for (Item item : items) {
            if (!isSulfuras(item)) {
                item.sellIn = item.sellIn - 1;
            }

            QualityRule rule = NAMED_ITEM_QUALITY_RULES.getOrDefault(item.name, getDefaultQualityRule(item));
            item.quality = rule.newQuality(item);

            enforceQualityRange(item);
        }
    }

    private QualityRule getDefaultQualityRule(Item item) {
        return item.name.startsWith("Conjured ") ? CONJURED_QUALITY_RULE : STANDARD_QUALITY_RULE;
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
