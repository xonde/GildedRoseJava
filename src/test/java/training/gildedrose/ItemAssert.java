package training.gildedrose;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.description.Description;

public class ItemAssert extends AbstractAssert<ItemAssert, Item> {
    public ItemAssert(Item actual) {
        super(actual, ItemAssert.class);
    }

    public static ItemAssert assertThat(Item actual) {
        return new ItemAssert(actual);
    }

    public ItemAssert hasQuality(int quality) {
        isNotNull();

        if (actual.quality != quality) {
            failWithMessage("Expected item's quality to be <%d> but was <%d>", quality, actual.quality);
        }

        return this;
    }

    public ItemAssert hasSellIn(int sellIn) {
        isNotNull();

        if (actual.sellIn != sellIn) {
            failWithMessage("Expected item's 'sell in' to be <%d> but was <%d>", sellIn, actual.sellIn);
        }

        return this;
    }
}
