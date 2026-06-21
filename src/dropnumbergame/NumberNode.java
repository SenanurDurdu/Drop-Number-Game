/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dropnumbergame;

/**
 *
 * @author durdu
 */


//Bu sınıf, oyunda kullanılacak sayı sırasını tutmak için
// kullanılır. Array kullanmadan linked list mantığıyla ilerleme sağlar.
class NumberNode {

    int value; // O anki sayı değerini verir.(2, 4, 8, ...)
    
    NumberNode next; // Listedeki bir sonraki sayı.
    
    public NumberNode(int value) {  //Yeni bir sayı düğümü oluşturur
     
        this.value = value; //Sayı değeri atanır
    }
}
