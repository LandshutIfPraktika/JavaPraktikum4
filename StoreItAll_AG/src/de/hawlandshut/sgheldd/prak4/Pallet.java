package de.hawlandshut.sgheldd.prak4;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

/**
 * This class implements a storage pallet.
 * </p>
 * It can be found by a unique ID.
 * <p>
 * Each pallet can store a list of strings, representing the items stored.
 * Created by s-gheldd on 5/12/15.
 *
 * @author Georg Held
 */
public class Pallet {
    private final static int DEFAULT_SIZE = 10;

    private final String id;
    private final String[] articleList;

    private int nextFreeSlot = 0;

    /**
     * Constructs a new pallet object, automatically assigns an unique ID.
     *
     * @param size the maximum storage size, of the created pallet
     */
    public Pallet(int size) {
        this.id = "P" + UUID.randomUUID().toString();
        this.articleList = new String[size];
    }

    /**
     * Constructs a new pallet object, of the DEFAULT_SIZE.
     * Automatically assigns an unique ID.
     */
    public Pallet() {
        this(DEFAULT_SIZE);
    }

    /**
     * This makes actually no sense to me, why would you need the ability to clone a pallet?
     *
     * @param that another pallet
     */
    public Pallet(Pallet that) {
        this.articleList = new String[that.articleList.length];
        this.id = new String(that.id);
        this.nextFreeSlot = that.nextFreeSlot;
        for (int i = 0; i < that.articleList.length; i++) {
            if (that.articleList[i] != null)
                this.articleList[i] = new String(that.articleList[i]);
        }
    }

    /**
     * Getter for the unique ID of a pallet.
     *
     * @return "P"+UUID as a string
     */
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        String answer = "Pallet: " + this.id + "\n" + "Size: " + this.articleList.length + "\n" + "Contains:\n";
        for (String element : this.articleList) {
            answer += element + ", ";
        }
        return answer;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj) {
            return true;
        } else if (obj instanceof Pallet) {
            Pallet pallet;
            pallet = (Pallet) obj;

            if (this.id.equals(pallet.id) && this.articleList.length == pallet.articleList.length &&
                    this.nextFreeSlot == pallet.nextFreeSlot) {
                for (int i = 0; i < this.nextFreeSlot; i++) {
                    if (!this.articleList[i].equals(pallet.articleList[i]))
                        return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + Arrays.hashCode(articleList);
        result = 31 * result + nextFreeSlot;
        return result;
    }

    /**
     * Stores an item on the pallet. Throws an PALLET_FULL_EXCEPTION if the pallet is full,
     * and no additional item can be stored.
     *
     * @param article the to be stored item
     * @return this
     * @throws PALLET_FULL_EXCEPTION
     */
    public Pallet store(String article) throws PALLET_FULL_EXCEPTION {
        if (this.nextFreeSlot < this.articleList.length) {
            articleList[this.nextFreeSlot] = article;
            this.nextFreeSlot++;
        } else {
            throw new PALLET_FULL_EXCEPTION("No more free slots in pallet: " + this.id + " " + LocalDateTime.now());
        }
        return this;
    }

    /**
     * Stores an ellipse of items on the pallet. Throws an PALLET_FULL_EXCEPTION if the pallet is full,
     * and no additional items can be stored.
     *
     * @param articles Ellipse of articles to be stored.
     * @return this
     * @throws PALLET_FULL_EXCEPTION
     */
    public Pallet store(String... articles) throws PALLET_FULL_EXCEPTION {
        for (String item : articles) {
            this.store(item);
        }
        return this;
    }

    /**
     * Searches if an item is stored on the pallet.
     *
     * @param article the to be found item
     * @return article if it is stored, else null
     */
    public String searchArticle(String article) {
        for (String element : this.articleList) {
            if (article.equals(element))
                return article;
        }

        return null;
    }
}
