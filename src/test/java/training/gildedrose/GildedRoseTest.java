package training.gildedrose;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static training.gildedrose.ItemAssert.*;
import static training.gildedrose.ItemAssert.assertThat;

public class GildedRoseTest {

    @Test
    public void sulfurasDoesNotAgeOrChangeInQuality() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 5, 80);
        GildedRose gildedRose = new GildedRose(new Item[]{sulfuras});

        for (int i = 0; i < 10; i++) {
            gildedRose.updateQuality();
        }

        assertThat(sulfuras).hasSellIn(5);
        assertThat(sulfuras).hasQuality(80);
    }

    @Test
    public void standardItemQualityDecreasesNormallyBeforeExpiry() {
        Item item = new Item("Standard item", 10, 20);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        for (int i = 0; i < 5; i++) {
            gildedRose.updateQuality();
        }

        assertThat(item).hasSellIn(5);
        assertThat(item).hasQuality(15);
    }

    @Test
    public void standardItemQualityDecreasesFasterAfterExpiry() {
        Item item = new Item("Standard Item", 1, 10);

        GildedRose gildedRose = new GildedRose(new Item[]{item});

        for (int i = 0; i < 3; i++) {
            gildedRose.updateQuality();
        }

        assertThat(item).hasSellIn(-2);
        assertThat(item).hasQuality(5);
    }

    @Test
    public void itemQualityDoesNotGoBelowZero() {
        Item item = new Item("Standard Item", 5, 10);

        GildedRose gildedRose = new GildedRose(new Item[]{item});

        for (int i = 0; i < 10; i++) {
            gildedRose.updateQuality();
        }

        assertThat(item).hasQuality(0);
    }

    @Test
    public void brieQualityIncreasesNormallyBeforeExpiry() {
        Item brie = new Item("Aged Brie", 10, 20);
        GildedRose gildedRose = new GildedRose(new Item[]{brie});

        for (int i = 0; i < 5; i++) {
            gildedRose.updateQuality();
        }

        assertThat(brie).hasSellIn(5);
        assertThat(brie).hasQuality(25);
    }

    @Test
    public void brieQualityIncreasesFasterAfterExpiry() {
        Item brie = new Item("Aged Brie", 1, 10);

        GildedRose gildedRose = new GildedRose(new Item[]{brie});

        for (int i = 0; i < 3; i++) {
            gildedRose.updateQuality();
        }

        assertThat(brie).hasSellIn(-2);
        assertThat(brie).hasQuality(15);
    }

    @Test
    public void itemQualityDoesNotGoAboveFifty() {
        Item brie = new Item("Aged Brie", 5, 40);

        GildedRose gildedRose = new GildedRose(new Item[]{brie});

        for (int i = 0; i < 10; i++) {
            gildedRose.updateQuality();
        }

        assertThat(brie).hasQuality(50);
    }

    @Test
    public void backstagePassValueIncreasesNormallyBeforeTenDayPoint() {
        Item pass = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);

        GildedRose gildedRose = new GildedRose(new Item[]{pass});

        for (int i = 0; i < 5; i++) {
            gildedRose.updateQuality();
        }

        assertThat(pass).hasSellIn(10);
        assertThat(pass).hasQuality(25);
    }

    @Test
    public void backstagePassValueIncreasesAtDoubleRateBetweenTenAndFiveDayPoint() {
        Item pass = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);

        GildedRose gildedRose = new GildedRose(new Item[]{pass});

        for (int i = 0; i < 5; i++) {
            gildedRose.updateQuality();
        }

        assertThat(pass).hasSellIn(5);
        assertThat(pass).hasQuality(30);
    }

    @Test
    public void backstagePassValueIncreasesAtTripleRateBetweenFiveDayPointAndExpiry() {
        Item pass = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);

        GildedRose gildedRose = new GildedRose(new Item[]{pass});

        for (int i = 0; i < 5; i++) {
            gildedRose.updateQuality();
        }

        assertThat(pass).hasSellIn(0);
        assertThat(pass).hasQuality(35);
    }

    @Test
    public void backstagePassValueIsZeroAfterExpiry() {
        Item pass = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);

        GildedRose gildedRose = new GildedRose(new Item[]{pass});

        gildedRose.updateQuality();

        assertThat(pass).hasSellIn(-1);
        assertThat(pass).hasQuality(0);
    }

    @Test
    public void backstagePassValueRemainsZeroAfterExpiry() {
        Item pass = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0);

        GildedRose gildedRose = new GildedRose(new Item[]{pass});

        for (int i = 0; i < 5; i++) {
            gildedRose.updateQuality();
        }

        assertThat(pass).hasQuality(0);
    }

    @Test
    public void conjuredItemQualityDecreasesAtDoubleRateBeforeExpiry() {
        Item item = new Item("Conjured item", 10, 20);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        for (int i = 0; i < 5; i++) {
            gildedRose.updateQuality();
        }

        assertThat(item).hasSellIn(5);
        assertThat(item).hasQuality(10);
    }

    @Test
    public void conjuredItemQualityDecreasesAtQuadrupleRateAfterExpiry() {
        Item item = new Item("Conjured Item", 1, 20);

        GildedRose gildedRose = new GildedRose(new Item[]{item});

        for (int i = 0; i < 3; i++) {
            gildedRose.updateQuality();
        }

        assertThat(item).hasSellIn(-2);
        assertThat(item).hasQuality(10);
    }
}