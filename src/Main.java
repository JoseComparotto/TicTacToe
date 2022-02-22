
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author José Augusto Queiroz COmparotto Gomes
 */
public class Main extends javax.swing.JFrame {

    // Constantes do tauleiro
    static final char EMPTY = ' ';
    static final char X = 'X';
    static final char O = 'O';

    // Estados do jogo
    static final int RUNNING = 0;   // O Jogo ainda não acabou
    static final int X_WON = 1;     // X venceu
    static final int O_WON = 2;     // O Venceu
    static final int TIE = 3;       // Velha

    char[][] table = new char[3][3];    // Matriz de characteres 3x3 que representa o tabuleiro
    char currentPlayer = X;             // Jogador Atual    
    int gameState = RUNNING;            // Estado do Jogo

    /**
     * Inicia (ou reinicia) o jogo 
     */
    void initGame(){

        clearTable();

        currentPlayer = X;
        gameState = RUNNING;

        updateScreen();

    }

    /**
     * Limpa todos os espaços do tabuleiro
     */
    void clearTable(){

        this.table = new char[][]{
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
        };

    }

    /**
     * Atualiza os textos dos elementos gráficos
     */
    void updateScreen(){

        switch(currentPlayer){
            case X:
                lblStatus.setText("É a vez de X.");
                break;

            case O:
                lblStatus.setText("É a vez de O.");
                break;
            default:
                lblStatus.setText("");
                break;
        }

        switch(gameState){
            case X_WON:
                lblStatus.setText("X venceu!");
                break;

            case O_WON:
                lblStatus.setText("O venceu!");
                break;

            case TIE:
                lblStatus.setText("Deu velha!");
                break;

            default:
                break;
        }

        L1C1.setText(String.valueOf(table[0][0]));
        L1C2.setText(String.valueOf(table[0][1]));
        L1C3.setText(String.valueOf(table[0][2]));
        L2C1.setText(String.valueOf(table[1][0]));
        L2C2.setText(String.valueOf(table[1][1]));
        L2C3.setText(String.valueOf(table[1][2]));
        L3C1.setText(String.valueOf(table[2][0]));
        L3C2.setText(String.valueOf(table[2][1]));
        L3C3.setText(String.valueOf(table[2][2]));

    }

    /**
     * Troca o jogador da vez
     */
    void changePlayer(){

        if(currentPlayer == X){
            currentPlayer = O;
        }else if(currentPlayer == O){
            currentPlayer = X;
        }

    }

    /**
     * Executa uma jogada
     */
    void play(int row, int col){
    
        if(gameState != RUNNING){  // Se o jogo já acabou, um novo jogo se inicia
            initGame();
            return;
        }

        if(table[row][col] != EMPTY) return;  // Não executa a jogada se a casa estiver ocupada

        table[row][col] = currentPlayer;

        changePlayer();

        verifyGameState();
        
        updateScreen();

    }

    /**
     * Verifica se o jogo acabou
     */
    void verifyGameState(){

        // Linha X
        if(table[0][0] == X && table[0][1] == X && table[0][2] == X) {
            gameState = X_WON;
            return;
        }
        if(table[1][0] == X && table[1][1] == X && table[1][2] == X) {
            gameState = X_WON;
            return;
        }
        if(table[2][0] == X && table[2][1] == X && table[2][2] == X) {
            gameState = X_WON;
            return;
        }

        // Coluna X
        if(table[0][0] == X && table[1][0] == X && table[2][0] == X) {
            gameState = X_WON;
            return;
        }
        if(table[0][1] == X && table[1][1] == X && table[2][1] == X) {
            gameState = X_WON;
            return;
        }
        if(table[0][2] == X && table[1][2] == X && table[2][2] == X) {
            gameState = X_WON;
            return;
        }

        //Diagonal X
        if(table[0][0] == X && table[1][1] == X && table[2][2] == X) {
            gameState = X_WON;
            return;
        }
        if(table[0][2] == X && table[1][1] == X && table[2][0] == X) {
            gameState = X_WON;
            return;
        }


        // Linha O
        if(table[0][0] == O && table[0][1] == O && table[0][2] == O) {
            gameState = O_WON;
            return;
        }
        if(table[1][0] == O && table[1][1] == O && table[1][2] == O) {
            gameState = O_WON;
            return;
        }
        if(table[2][0] == O && table[2][1] == O && table[2][2] == O) {
            gameState = O_WON;
            return;
        }

        // Coluna O
        if(table[0][0] == O && table[1][0] == O && table[2][0] == O) {
            gameState = O_WON;
            return;
        }
        if(table[0][1] == O && table[1][1] == O && table[2][1] == O) {
            gameState = O_WON;
            return;
        }
        if(table[0][2] == O && table[1][2] == O && table[2][2] == O) {
            gameState = O_WON;
            return;
        }

        //Diagonal O
        if(table[0][0] == O && table[1][1] == O && table[2][2] == O) {
            gameState = O_WON;
            return;
        }
        if(table[0][2] == O && table[1][1] == O && table[2][0] == O) {
            gameState = O_WON;
            return;
        }

        boolean hasEmpty = false;

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(table[row][col] == EMPTY) {
                    hasEmpty = true;
                    break;
                }
            }
        }

        if(!hasEmpty){   // Se o jogo ainda não acabou e não exite uma posição vasia, deu velha
            gameState = TIE;
            return;        
        }

    }


    public Main() {
        initComponents();

        this.setLocationRelativeTo(null); // Faz a Janela ser invocada no centro da tela

        initGame();

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        L1C1 = new javax.swing.JButton();
        L1C2 = new javax.swing.JButton();
        L1C3 = new javax.swing.JButton();
        L2C1 = new javax.swing.JButton();
        L2C2 = new javax.swing.JButton();
        L2C3 = new javax.swing.JButton();
        L3C1 = new javax.swing.JButton();
        L3C2 = new javax.swing.JButton();
        L3C3 = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblCredits = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jogo da Velha");
        setResizable(false);

        L1C1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        L1C1.setText("X");
        L1C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L1C1ActionPerformed(evt);
            }
        });

        L1C2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        L1C2.setText("X");
        L1C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L1C2ActionPerformed(evt);
            }
        });

        L1C3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        L1C3.setText("X");
        L1C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L1C3ActionPerformed(evt);
            }
        });

        L2C1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        L2C1.setText("X");
        L2C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L2C1ActionPerformed(evt);
            }
        });

        L2C2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        L2C2.setText("X");
        L2C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L2C2ActionPerformed(evt);
            }
        });

        L2C3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        L2C3.setText("X");
        L2C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L2C3ActionPerformed(evt);
            }
        });

        L3C1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        L3C1.setText("X");
        L3C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L3C1ActionPerformed(evt);
            }
        });

        L3C2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        L3C2.setText("X");
        L3C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L3C2ActionPerformed(evt);
            }
        });

        L3C3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        L3C3.setText("X");
        L3C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L3C3ActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Jogo da Velha");
        lblTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(0, 0, 153));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblCredits.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        lblCredits.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCredits.setText("Desenvolvido por José Comparotto");
        lblCredits.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 68, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(L3C1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L3C2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L3C3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(L2C1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L2C2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L2C3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(L1C1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L1C2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L1C3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 68, Short.MAX_VALUE))
                    .addComponent(lblCredits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L1C1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L1C2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L1C3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L2C1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2C2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2C3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L3C1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L3C2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L3C3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(lblCredits)
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void L1C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L1C1ActionPerformed
        /**
         * Ação do botão da Linha 1, Coluna 1
         * 
         * [#][ ][ ]
         * [ ][ ][ ]
         * [ ][ ][ ]
         */
        play(0,0);
    }//GEN-LAST:event_L1C1ActionPerformed

    private void L1C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L1C2ActionPerformed
        /**
         * Ação do botão da Linha 1, Coluna 2
         * 
         * [ ][#][ ]
         * [ ][ ][ ]
         * [ ][ ][ ]
         */
        play(0,1);
    }//GEN-LAST:event_L1C2ActionPerformed

    private void L1C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L1C3ActionPerformed
        /**
         * Ação do botão da Linha 1, Coluna 3
         * 
         * [ ][ ][#]
         * [ ][ ][ ]
         * [ ][ ][ ]
         */
        play(0,2);
    }//GEN-LAST:event_L1C3ActionPerformed

    private void L2C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L2C1ActionPerformed
        /**
         * Ação do botão da Linha 2, Coluna 1
         * 
         * [ ][ ][ ]
         * [#][ ][ ]
         * [ ][ ][ ]
         */
        play(1,0);
    }//GEN-LAST:event_L2C1ActionPerformed

    private void L2C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L2C2ActionPerformed
        /**
         * Ação do botão da Linha 2, Coluna 2
         * 
         * [ ][ ][ ]
         * [ ][#][ ]
         * [ ][ ][ ]
         */
        play(1,1);
    }//GEN-LAST:event_L2C2ActionPerformed

    private void L2C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L2C3ActionPerformed
        /**
         * Ação do botão da Linha 2, Coluna 3
         * 
         * [ ][ ][ ]
         * [ ][ ][#]
         * [ ][ ][ ]
         */
        play(1,2);
    }//GEN-LAST:event_L2C3ActionPerformed

    private void L3C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L3C1ActionPerformed
        /**
         * Ação do botão da Linha 3, Coluna 1
         * 
         * [ ][ ][ ]
         * [ ][ ][ ]
         * [#][ ][ ]
         */
        play(2,0);
    }//GEN-LAST:event_L3C1ActionPerformed

    private void L3C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L3C2ActionPerformed
        /**
         * Ação do botão da Linha 3, Coluna 2
         * 
         * [ ][ ][ ]
         * [ ][ ][ ]
         * [ ][#][ ]
         */
        play(2,1);
    }//GEN-LAST:event_L3C2ActionPerformed

    private void L3C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L3C3ActionPerformed
        /**
         * Ação do botão da Linha 3, Coluna 3
         * 
         * [ ][ ][ ]
         * [ ][ ][ ]
         * [ ][ ][#]
         */
        play(2,2);
    }//GEN-LAST:event_L3C3ActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton L1C1;
    private javax.swing.JButton L1C2;
    private javax.swing.JButton L1C3;
    private javax.swing.JButton L2C1;
    private javax.swing.JButton L2C2;
    private javax.swing.JButton L2C3;
    private javax.swing.JButton L3C1;
    private javax.swing.JButton L3C2;
    private javax.swing.JButton L3C3;
    private javax.swing.JLabel lblCredits;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}
