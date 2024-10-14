package com.toolshop.data;

import java.util.ArrayList;
import java.util.List;

import com.toolshop.model.ItemListing;

public class ItemListingRepository {
    
    private final List<ItemListing> itemListings = new ArrayList<>();

    // Ladder $1.99 Yes Yes No
// Chainsaw $1.49 Yes No Yes
// Jackhammer $2.99 Yes No No
    public ItemListingRepository(){
        itemListings.add(new ItemListing("Ladder",1.99,true, true, false));
        itemListings.add(new ItemListing("Chainsaw",1.49, true, false, true));
        itemListings.add(new ItemListing("Jackhammer",2.99, true, false, false));
    }

    public List<ItemListing> getItemListings() {
        return itemListings;
    }
}