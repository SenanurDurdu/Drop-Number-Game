/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dropnumbergame;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author durdu
 */
// Bu class oyunun GUI kısmını yönetir. MultiLinkedList ile tutulan veriyi ekranda gösterir.
public class DropNumberFrame extends javax.swing.JFrame {

    private MultiLinkedList game; // Oyunun veri yapısı (Multilinked List)

    private NumberNode currentNumber; // Sayıların sırasını tutan linked list'in current node'u

    private JLabel[][] labels; // Griddeki JLabel'lara kolay erişmek için 2D array kullanıyoruz.

    public DropNumberFrame() {
        game = new MultiLinkedList();  // Veri yapısını oluşturur.
        setupSequence();               // Sayı dizisini bağlı listeye çevirir.
        initComponents();              
        initializeLabelMatrix();       // JLabel'ları matrise yerleştirir.
        updateSteps();                 // İlk boş gridi gösterir.
    }

    
    // Bize verilen column sırasıdıe. Hangi sırada düşeceğini gösterir.
    private int[] columnsSequence = {
        0, 3, 1, 2, 4, 1, 4, 0, 0, 1,
        2, 2, 3, 1, 2, 0, 4, 2, 1, 3, 3,
        3, 3, 1, 2, 2, 2, 2, 2, 1, 1, 1
    };

    private int step_num = 0; // Kaçıncı adımda olduğumuzu tutar.

    //Sayı dizisini linked liste çevirir. Array kullanmadan sırayla ilerlemek için kullanılır.
    private void setupSequence() {
        int[] values = {2, 2, 4, 2, 4, 2, 4, 8, 8, 32, 2, 64, 16, 64, 32, 16, 16, 32, 64, 8, 4, 2, 2, 2, 64, 32, 16, 8, 8, 4, 8};

        NumberNode head = new NumberNode(values[0]); // İlk node oluşturulur.
        NumberNode temp = head;

        // Geri kalan elemanlar linked list'e eklenir.
        for (int i = 1; i < values.length; i++) {
            temp.next = new NumberNode(values[i]);
            temp = temp.next;
        }
        currentNumber = head;  // currentNumber başlangıç node'u (head) olur.
    }

    //JPanel içine attığımız JLabel'ları 7x5'lik matrise dönüştürür.
    private void initializeLabelMatrix() {
        labels = new JLabel[7][5];

        Component[] comps = jPanel1.getComponents();  // Paneldeki tüm componentleri alırız.
        int index = 0;

        // Grid mantığıyla matrise yerleştiririz.
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                if (index < comps.length && comps[index] instanceof JLabel) {
                    labels[i][j] = (JLabel) comps[index];

                    labels[i][j].setHorizontalAlignment(SwingConstants.CENTER);  // Yazıyı ortalamaya yarar.

                    labels[i][j].setOpaque(true); // Arka plan boyanabilir olmayı sağlar.
                }
                index++;
            }
        }
    }

    private Color getColorForValue(int val) { //Sayıya göre renk döndürür.
        if (val == 2) {
            return new Color(238, 228, 218);
        }
        if (val == 4) {
            return new Color(237, 224, 200);
        }
        if (val == 8) {
            return new Color(242, 177, 121);
        }
        if (val == 16) {
            return new Color(245, 149, 99);
        }
        if (val == 32) {
            return new Color(246, 124, 95);
        }

        return Color.ORANGE;  // Büyük sayılar için default renk belirlenir.
    }

    private void processNextStep() {  //Her butona basıldığında, Yeni sayı düşer ,Linked list ilerler ve GUI güncellenir.

        if (currentNumber != null && step_num < columnsSequence.length) {   // Eğer sayı kaldıysa ve senaryo bitmediyse kontrolü yapılır.

            int targetColumn = columnsSequence[step_num]; // Hangi sütuna düşeceği bulunur.

            int val = currentNumber.value;   // O anki sayı değeri atanır.

            System.out.println("Step " + (step_num + 1) + ": Column " + targetColumn + " -> Value " + val);  // Konsola debug çıktısı verilir.

            game.dropping(targetColumn, val); // Sayıyı grid'e bırakırız.

            currentNumber = currentNumber.next; // Bir sonraki sayıya geçeriz.

            step_num++; // Adımı artırır.
            updateSteps(); // GUI günceller.
            game.printGrid(); // Konsolda grid yazdırır.
        } else {
            JOptionPane.showMessageDialog(this, "Scenario Completed !! Final value is 256 ", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateSteps() { // LinkedList'teki veriyi GUI'ye yansıtır.

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {

                Node node = game.findNode(6 - i, j); // GUI üstten başlar ama veri yapısı alttan başlar bu yüzden 6 - i yazaarız.

                if (node != null) {  // Hücre doluysa değeri yazarız.

                    labels[i][j].setText(String.valueOf(node.value));
                    labels[i][j].setBackground(getColorForValue(node.value)); // Rengi ayarlarız.
                } else {
                    labels[i][j].setText("");  // Boşsa temizleriz.
                    labels[i][j].setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        btnDropNumbers = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 700));
        jPanel1.setLayout(new java.awt.GridLayout(7, 5));

        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);

        jLabel8.setText("jLabel8");
        jPanel1.add(jLabel8);

        jLabel34.setText("jLabel34");
        jPanel1.add(jLabel34);

        jLabel29.setText("jLabel29");
        jPanel1.add(jLabel29);

        jLabel14.setText("jLabel14");
        jPanel1.add(jLabel14);

        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2);

        jLabel13.setText("jLabel13");
        jPanel1.add(jLabel13);

        jLabel22.setText("jLabel22");
        jPanel1.add(jLabel22);

        jLabel30.setText("jLabel30");
        jPanel1.add(jLabel30);

        jLabel21.setText("jLabel21");
        jPanel1.add(jLabel21);

        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3);

        jLabel33.setText("jLabel33");
        jPanel1.add(jLabel33);

        jLabel32.setText("jLabel32");
        jPanel1.add(jLabel32);

        jLabel31.setText("jLabel31");
        jPanel1.add(jLabel31);

        jLabel28.setText("jLabel28");
        jPanel1.add(jLabel28);

        jLabel4.setText("jLabel4");
        jPanel1.add(jLabel4);

        jLabel26.setText("jLabel26");
        jPanel1.add(jLabel26);

        jLabel19.setText("jLabel19");
        jPanel1.add(jLabel19);

        jLabel18.setText("jLabel18");
        jPanel1.add(jLabel18);

        jLabel27.setText("jLabel27");
        jPanel1.add(jLabel27);

        jLabel5.setText("jLabel5");
        jPanel1.add(jLabel5);

        jLabel17.setText("jLabel17");
        jPanel1.add(jLabel17);

        jLabel20.setText("jLabel20");
        jPanel1.add(jLabel20);

        jLabel25.setText("jLabel25");
        jPanel1.add(jLabel25);

        jLabel24.setText("jLabel24");
        jPanel1.add(jLabel24);

        jLabel6.setText("jLabel6");
        jPanel1.add(jLabel6);

        jLabel23.setText("jLabel23");
        jPanel1.add(jLabel23);

        jLabel16.setText("jLabel16");
        jPanel1.add(jLabel16);

        jLabel15.setText("jLabel15");
        jPanel1.add(jLabel15);

        jLabel9.setText("jLabel9");
        jPanel1.add(jLabel9);

        jLabel7.setText("jLabel7");
        jPanel1.add(jLabel7);

        jLabel11.setText("jLabel11");
        jPanel1.add(jLabel11);

        jLabel12.setText("jLabel12");
        jPanel1.add(jLabel12);

        jLabel10.setText("jLabel10");
        jPanel1.add(jLabel10);

        jLabel35.setText("jLabel35");
        jPanel1.add(jLabel35);

        btnDropNumbers.setText("Drop Numbers");
        btnDropNumbers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDropNumbersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDropNumbers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(btnDropNumbers)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDropNumbersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDropNumbersActionPerformed
        // TODO add your handling code here:
        processNextStep();   // Bir adım ilerletmek için metodu çağırırız.
    }//GEN-LAST:event_btnDropNumbersActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DropNumberFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DropNumberFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DropNumberFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DropNumberFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DropNumberFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDropNumbers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
