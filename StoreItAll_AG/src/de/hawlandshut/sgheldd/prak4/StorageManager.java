package de.hawlandshut.sgheldd.prak4;

/**
 * This class implements a storage manager of pallets.
 * <p>
 * Created by s-gheldd on 5/17/15.
 *
 * @author Georg Held
 * @see Pallet
 */
public class StorageManager {
    private Pallet[][][] storage;

    /**
     * Creates a new storage manager for pallets.
     *
     * @param rowSize    Maximum numbers of rows
     * @param columnSize Maximum number of columns
     * @param levelSize  Maximum number of levels
     */
    public StorageManager(int rowSize, int columnSize, int levelSize) {
        this.storage = new Pallet[rowSize][columnSize][levelSize];
    }

    /**
     * Stores a pallet at the specified location. Throws STORAGE_OCCUPIED_EXCEPTION if location does not exist or
     * location is already occupied by another pallet.
     *
     * @param pallet to be stored pallet
     * @param row    row location
     * @param column column location
     * @param level  level location
     * @throws STORAGE_OCCUPIED_EXCEPTION
     */
    public void storePallet(Pallet pallet, int row, int column, int level) throws STORAGE_OCCUPIED_EXCEPTION {
        if (this.storage.length < row || this.storage[row].length < column || this.storage[row][column].length < level
                || row < 0 || column < 0 || level < 0) {
            throw new STORAGE_OCCUPIED_EXCEPTION("This storage space does not exist!");
        }
        if (this.storage[row][column][level] == null) {
            this.storage[row][column][level] = pallet;
        } else {
            throw new STORAGE_OCCUPIED_EXCEPTION("Space at: " + row + ", " + column + ", " + level + " is not free!");
        }
    }

    /**
     * Searches storage for a pallet by a given palletId. Returns null if no matching pallet is found.
     *
     * @param palletId Id of the to be searched pallet
     * @return the found pallet or null if not found
     */
    public Pallet getPalletById(String palletId){
        for  (Pallet[][] row :this.storage){
            for (Pallet[] column :row){
                for (Pallet level :column){
                    if (level != null && palletId.equals(level.getId())){
                        return level;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Searches storage for an article. Returns the first found instance of the article or null if not found.
     * @param article the to be found article
     * @return the first matching article or null if not found
     */
    public String searchArticle(String article){
        String foundArticle;
        for (Pallet[][] row :this.storage){
            for (Pallet[] column :row){
                for (Pallet level : column){
                    if (level != null && (foundArticle = level.searchArticle(article)) !=null ){
                        return foundArticle;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Checks if a given pallet is in storage.
     * @param pallet the to be checked pallet
     * @return true if fouind false otherwise
     */
    public boolean containsPallet(Pallet pallet){
        if (this.getPalletById(pallet.getId())!= null) return true;
        else return false;
    }

    /**
     * Prints every non null pallet in storage plus it's position.
     */
    public void printStorage(){
        for (int i = 0; i<this.storage.length; i++){
            for (int j = 0; j < this.storage[i].length; j++){
                for (int k = 0; k < this.storage[i][j].length;k++ ){
                    if(this.storage[i][j][k] != null){
                        System.out.println(i + ", "+ j +", " + k + ": "+ storage[i][j][k].toString());
                        System.out.println();
                    }
                }
            }
        }
    }

}
