package dropnumbergame;

/**
 * @author durdu
 */
//Bu sınıf oyunun veri yapısını tutar. 2D grid mantığını linked list + pointer (up, down, left, right) ile gösterilir.
public class MultiLinkedList {

    Node head = null;  // Listenin başlangıç noktasıdır.

    int maxRow = 7;   // Grid boyutları (belirlenir.
    int maxCol = 5;

    // Yeni bir sayıyı verilen sütuna düşürür (drop).
    public void dropping(int col, int value) {

        int targetRow = getLowestEmptyRow(col);  // O sütundaki en alt boş satırı buluruz.

        if (targetRow >= maxRow) {   // Eğer sütun doluysa işlem yapmaz.
            System.out.println("Column" + col + " is full!");
            return;
        }
        Node newNode = new Node(value, targetRow, col);  // Yeni node oluştur (row, col, value).
        addNodeToList(newNode);   // Listeye eklenir.
        linkNeighbors(newNode);  // Komşularla bağlantı kurulur.
        checkMerge(newNode); // Merge (birleşme) kontrolü yapılır.
    }

    // Verilen sütunda en alt boş satırı bulur ve oraya düşmek sağlanır.
    private int getLowestEmptyRow(int col) {

        int highestRow = -1;
        Node temp = head;

        while (temp != null) {   // Aynı sütundaki en büyük row değerini buluruz. (dolu olan).
            if (temp.col == col && temp.row > highestRow) {
                highestRow = temp.row;
            }
            temp = temp.next;
        }

        return highestRow + 1; // Bu node'un bir üst satır boş demektir.
    }

    // Yeni node'u linked list'in sonuna ekleriz.
    private void addNodeToList(Node newNode) {

        if (head == null) {   // Eğer liste boşsa direkt head olur.
            head = newNode;
        } else {
            Node temp = head;

            while (temp.next != null) {   // Listenin sonuna gidilir.
                temp = temp.next;
            }
            temp.next = newNode;  // Sonuna ekleriz.
        }
    }

    // Node'u komşularına bağlarız. (up, down, left, right)
    private void linkNeighbors(Node node) {
        Node temp = head;
        while (temp != null) {
            // Kendisiyle karşılaştırma yaparız.
            if (temp == node) {
                temp = temp.next;
                continue;
            }

            
            // Altındaki node ile bağlantı kurarız.
            if (temp.col == node.col && temp.row == node.row - 1) {
                node.down = temp;
                temp.up = node;
            }
            // Üstündeki node ile bağlantı kurarız.
            if (temp.col == node.col && temp.row == node.row + 1) {
                node.up = temp;
                temp.down = node;
            }
            
            // Solundaki node ile bağlantı kurarız.
            if (temp.row == node.row && temp.col == node.col - 1) {
                node.left = temp;
                temp.right = node;
            }

            // Sağındaki node ile bağlantı kurarız.
            if (temp.row == node.row && temp.col == node.col + 1) {
                node.right = temp;
                temp.left = node;
            }
            temp = temp.next;
        }
    }

    // Merge (birleşme) kontrolü yapatız aynı değerde altındaki node varsa birleştiririz. 
    private void checkMerge(Node node) {

        if (node != null && node.down != null && node.value == node.down.value) {  // Eğer alt node varsa ve değerler eşitse merge yaparız.
            node.down.value *= 2;   // Alt node'un değeri iki katına çıkarırız.
            removeNode(node);  // Üstteki node'u sileriz.
            checkMerge(node.down); // Zincirleme merge olabilir → tekrar kontrol ederiz.
        }
    }

    //Bir node'u listeden ve bağlantılardan sileriz.
    private void removeNode(Node target) {
        if (head == target) {  // Eğer silinecek node head ise diye kontrol ederiz.
            head = head.next;
        } else {
            Node temp = head;
            while (temp != null && temp.next != target) {  // Target'tan önceki node'u buluruz.
                temp = temp.next;
            }
            if (temp != null) {  // Listeden çıkarırız.
                temp.next = target.next;
            }
        }

        // Pointer temizleme kısmı
        if (target.left != null) {       // Sol bağlantıyı güncelliyoruz.
            target.left.right = target.right;
        }

        if (target.right != null) {    // Sağ bağlantıyı güncelliyoruz.
            target.right.left = target.left;
        }

        if (target.up != null) {     // Üst bağlantıyı güncelliyoruz.
            target.up.down = target.down;
        }

        if (target.down != null) {      // Alt bağlantıyı güncelliyoruz.
            target.down.up = target.up;
        }
    }

    // Verilen (row, col) koordinatındaki node'u buluruz.
    public Node findNode(int r, int c) {
        Node temp = head;
        while (temp != null) {
            if (temp.row == r && temp.col == c) {
                return temp;
            }
            temp = temp.next;
        }
        return null; // Bulunamazsa null döndürürüz.
    }

    //Grid'i konsola yazdırır (debug ve ödev çıktısı için)
    public void printGrid() {
        // En üst satırdan başlarız. (görsel düzen için)
        System.out.println("-----------------------------------");
        for (int r = maxRow - 1; r >= 0; r--) {
            for (int c = 0; c < maxCol; c++) {
                Node picture = findNode(r, c);
                if (picture == null) {
                    System.out.print("{    } "); // Boş hücre
                } else {
                    System.out.printf("{%4d} ", picture.value); // Dolu hücre
                }
            }
            System.out.println(); // Satır sonu
        }
    }
}
