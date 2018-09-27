package training.gildedrose;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class GildedRoseTest {


    @Test
    public void testThatAgedBrieGoesUpByOne(){
        Item[] list = new Item[1];
        list[0] = new Item("Aged Brie", 5, 30);
        GildedRosePrototype inventory = new GildedRosePrototype(list);


        inventory.updateQuality();

        assertThat(list[0].quality).isEqualTo(31);
    }

    @Test
    public void testThatAgedBrieGoesUpByTwo(){
        Item[] list = new Item[1];
        list[0] = new Item("Aged Brie", -1, 30);
        GildedRosePrototype inventory = new GildedRosePrototype(list);


        inventory.updateQuality();

        assertThat(list[0].quality).isEqualTo(32);
    }

    @Test
    public void testThatAgedBrieHasQualityLimit(){
        Item[] list = new Item[1];
        list[0] = new Item("Aged Brie", -1, 50);
        GildedRosePrototype inventory = new GildedRosePrototype(list);


        inventory.updateQuality();

        assertThat(list[0].quality).isEqualTo(50);
    }

    @Test
    public void testThatAgedBrieSellInGoesDown(){
        Item[] list = new Item[1];
        list[0] = new Item("Aged Brie", -1, 20);
        GildedRosePrototype inventory = new GildedRosePrototype(list);


        inventory.updateQuality();

        assertThat(list[0].sellIn).isEqualTo(-2);
    }

    @Test
    public void testThatSulfurasNeverChangesQuality(){
        Item[] list = new Item[1];
        list[0] = new Item("Sulfuras, Hand of Ragnaros", 5, 80);
        GildedRosePrototype inventory = new GildedRosePrototype(list);


        inventory.updateQuality();

        assertThat(list[0].quality).isEqualTo(80);
    }

    @Test
    public void testThatSulfurasNeverChangesSellIn(){
        Item[] list = new Item[1];
        list[0] = new Item("Sulfuras, Hand of Ragnaros", 50, 80);
        GildedRosePrototype inventory = new GildedRosePrototype(list);


        inventory.updateQuality();

        assertThat(list[0].sellIn).isEqualTo(50);
    }

    @Test
    public void testThatBackstagePassGoesUpByOne(){
        Item[] list = new Item[1];
        list[0] = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 43);
        GildedRosePrototype inventory = new GildedRosePrototype(list);


        inventory.updateQuality();

        assertThat(list[0].quality).isEqualTo(44);
    }

    @Test
    public void testThatBackstagePassGoesUpByTwo(){
        Item[] list = new Item[1];
        list[0] = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 43);
        GildedRosePrototype inventory = new GildedRosePrototype(list);


        inventory.updateQuality();

        assertThat(list[0].quality).isEqualTo(45);
    }

    @Test
    public void testThatBackstagePassGoesUpByThree(){
        Item[] list = new Item[1];
        list[0] = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 43);
        GildedRosePrototype inventory = new GildedRosePrototype(list);


        inventory.updateQuality();

        assertThat(list[0].quality).isEqualTo(46);
    }

    @Test
    public void testThatConjuredItemsGoDownByTwo(){
        Item[] list = new Item[1];
        list[0] = new Item("Conjured Shoe", 5, 43);
        GildedRosePrototype inventory = new GildedRosePrototype(list);


        inventory.updateQuality();

        assertThat(list[0].quality).isEqualTo(41);

    }

    @Test
    public void testThatConjuredItemsGoDownByFour(){
        Item[] list = new Item[1];
        list[0] = new Item("Conjured Shoe", -5, 43);
        GildedRosePrototype inventory = new GildedRosePrototype(list);


        inventory.updateQuality();

        assertThat(list[0].quality).isEqualTo(39);

    }

    @Test
    public void goldTest(){
        Item[] list = new Item[5];
        list[0] = new Item("Conjured Shoe", -5, 43);
        list[1] = new Item("Backstage passes to a TAFKAL80ETC concert", -5, 43);
        list[2] = new Item("Sulfuras, Hand of Ragnaros", -5, 43);
        list[3] = new Item("Aged Brie", -5, 43);
        list[4] = new Item("Apple", -5, 43);
        GildedRosePrototype inventory = new GildedRosePrototype(list);

//        for(i)
//        inventory.updateQuality();

        //assertThat(list[0].quality).isEqualTo(39);

    }



}