package game.entity;

public abstract class Item {
        private String name;
        private String type;
        private int price;

        public Item(String name, int price,String type) {
            this.name = name;
            this.price = price;
            this.type=type;
        }
        public String getName() {
            return this.name;
        }

        public String getType(){
                return this.type;}

        public int getPrice() {
            return this.price;
        }

}
