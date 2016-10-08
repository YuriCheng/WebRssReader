/*
 * Copyright 2004 Sun Microsystems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.sun.syndication.feed.synd.impl;

import com.sun.syndication.feed.rss.Category;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndCategoryI;
import com.sun.syndication.feed.synd.SyndEntryI;
import com.sun.syndication.feed.synd.SyndCategory;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class ConverterForRSS092 extends ConverterForRSS091 {

    public String getType() {
        return "rss_0.92";
    }

    protected SyndEntryI createSyndEntry(Item item) {
        SyndEntryI syndEntry = super.createSyndEntry(item);
        List cats =  item.getCategories();
        if (cats!=null) {
            syndEntry.setCategories(createSyndCategories(cats));
        }
        return syndEntry;
    }

    protected List createSyndCategories(List rssCats) {
        List syndCats = new ArrayList();
        for (int i=0;i<rssCats.size();i++) {
            Category rssCat = (Category) rssCats.get(i);
            SyndCategoryI sCat = new SyndCategory();
            sCat.setTaxonomyUri(rssCat.getDomain());
            sCat.setName(rssCat.getValue());
            syndCats.add(sCat);
        }
        return syndCats;
    }

    protected Item createRSSItem(SyndEntryI sEntry) {
        Item item = super.createRSSItem(sEntry);

        List sCats =  sEntry.getCategories();
        if (sCats!=null) {
            item.setCategories(createRSSCategories(sCats));
        }
        return item;
    }

    protected List createRSSCategories(List sCats) {
        List cats = new ArrayList();
        for (int i=0;i<sCats.size();i++) {
            SyndCategoryI sCat = (SyndCategoryI) sCats.get(i);
            Category cat = new Category();
            cat.setDomain(sCat.getTaxonomyUri());
            cat.setValue(sCat.getName());
            cats.add(cat);
        }
        return cats;
    }

}
