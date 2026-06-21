/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dropnumbergame;

/**
 *
 * @author durdu
 */



 //Bu sınıf griddeki her bir hücreyi temsil eder. Her node hem: 1) Linked list zincirine bağlıdır (next) 2)
// 2D grid komşularına bağlıdır (up, down, left, right)
 
public class Node {
 
    int value;// Hücredeki sayı (2, 4, 8, ...)
    int row, col; // Grid üzerindeki konum bilgisini tutacak.

    // 2D Grid bağlantıları
    Node up; // Üstteki komşu
    Node down;  // Alttaki komşu
    Node left;  // Soldaki komşu
    Node right; // Sağdaki komşu
    
    // Tüm node'ları tek zincir halinde gezmek için kullanılır (head üzerinden erişim sağlanır).
    Node next;

    public Node(int value, int row, int col) { //Node oluşturulurken değer ve konum atanır.    
        this.value = value; // Hücre değeri atanır.
        this.row = row;   // Konum bilgisi atanır.
        this.col = col;
    }
}
