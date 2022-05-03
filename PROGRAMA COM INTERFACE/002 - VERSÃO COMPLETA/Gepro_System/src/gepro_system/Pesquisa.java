/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gepro_system;

import javax.swing.JOptionPane;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author japin
 */

public class Pesquisa extends javax.swing.JFrame {
    
    /**
     * Creates new form Pesquisa
     */
    public class User {

        private int id_projetista;
        private String nome;
        private String especialidade;
        private String telefone;
        private String email;
        private String contrato;

        public User(int Id_Projetista, String Nome, String Especialidade, String Telefone, String Email, String Contrato) {
            this.id_projetista = Id_Projetista;
            this.nome = Nome;
            this.especialidade = Especialidade;
            this.telefone = Telefone;
            this.email = Email;
            this.contrato = Contrato;
        }

        public int getId_Projetista() {
            return id_projetista;
        }

        public String getNome() {
            return nome;
        }

        public String getEspecialidade() {
            return especialidade;
        }

        public String getTelefone() {
            return telefone;
        }

        public String getEmail() {
            return email;
        }

        public String getContrato() {
            return contrato;
        }
    }

    public class User2 {

        private int id_arquiteto;
        private String nome;
        private String telefone;
        private String email;
    
        public User2(int Id_Arquiteto, String Nome, String Telefone, String Email) {
            this.id_arquiteto = Id_Arquiteto;
            this.nome = Nome;
            this.telefone = Telefone;
            this.email = Email;
        }

        public int getId_Arquiteto() {
            return id_arquiteto;
        }

        public String getNome() {
            return nome;
        }

        public String getTelefone() {
            return telefone;
        }

        public String getEmail() {
            return email;
        }
    }
    
    public ArrayList<User> ListUsers(String Pesq1, String Pesq2, String Pesq3, String Pesq4, String Pesq5) {
        ArrayList<User> usersList = new ArrayList<User>();

        Statement st;
        ResultSet rs;

        try {
            Connection con = getConnection();
            st = con.createStatement();
            String searchQuery = "SELECT * FROM `projetista` where"
                    + " (nome like '%" + Pesq1 + "%' OR nome Is Null)"
                    + " AND (especialidade like '%" + Pesq2 + "%'OR especialidade Is NULL)"
                    + " AND (telefone like '%" + Pesq3 + "%'OR telefone Is NULL)"
                    + " AND (email like '%" + Pesq4 + "%'OR email Is NULL)"
                    + " AND (contrato like '%" + Pesq5 + "%' OR contrato is NULL)";
            rs = st.executeQuery(searchQuery);

            User user;

            while (rs.next()) {
                user = new User(
                        rs.getInt("id_projetista"),
                        rs.getString("nome"),
                        rs.getString("especialidade"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("contrato")
                );
                usersList.add(user);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return usersList;
    }

    public ArrayList<User2> ListUsers2(String Pesq1, String Pesq2, String Pesq3) {
        ArrayList<User2> usersList2 = new ArrayList<User2>();
        Statement st;
        ResultSet rs;
        try {
            Connection con = getConnection();
            st = con.createStatement();
            String searchQuery = "SELECT * FROM `arquiteto` where"
                    + " (nome like '%" + Pesq1 + "%' OR nome Is Null)"
                    + " AND (telefone like '%" + Pesq2 + "%'OR telefone Is NULL)"
                    + " AND (email like '%" + Pesq3+ "%'OR email Is NULL)";
            rs = st.executeQuery(searchQuery);

            User2 user2;

            while (rs.next()) {
                user2 = new User2(
                        rs.getInt("id_arquiteto"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("email")
                      
                );
                usersList2.add(user2);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return usersList2;
    }

    public void remove() {
         
        try {
            int input = JOptionPane.showConfirmDialog(null, "você deseja prosseguir? Essa operação não pode ser desfeita");
            if (input==0){
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj_pratico", "root", null);
            String sql;
            int row = PTABELA.getSelectedRow();
            String cell = PTABELA.getModel().getValueAt(row, 0).toString();
            String nominho = PTABELA.getModel().getValueAt(row, 1).toString();

            sql = "delete from projetista where id_projetista = " + "'" + cell + "'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "projetista " + nominho + " deletado com sucesso");
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void remove2() {
         
        try {
            int input = JOptionPane.showConfirmDialog(null, "você deseja prosseguir? Essa operação não pode ser desfeita");
            if (input==0){
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj_pratico", "root", null);
            String sql;
            int row = PTABELA2.getSelectedRow();
            String cell = PTABELA2.getModel().getValueAt(row, 0).toString();
            String nominho = PTABELA2.getModel().getValueAt(row, 1).toString();

            sql = "delete from arquiteto where id_arquiteto = " + "'" + cell + "'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "arquiteto " + nominho + " deletado com sucesso");
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    public void pesquisadeiro() {
        String contratoproj = "";
        if (jRadioButton1.isSelected()) {
            contratoproj = jRadioButton1.getText();
        }
        if (jRadioButton2.isSelected()) {
            contratoproj = jRadioButton2.getText();
        }

        ArrayList<User> users = ListUsers(nome.getText(), especialidade.getText(), telefone.getText(), email.getText(), contratoproj);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID", "NOME", "ESPECIALIDADE", "TELEFONE", "EMAIL", "CONTRATO"});
        Object[] row = new Object[6];

        for (int i = 0; i < users.size(); i++) {
            row[0] = users.get(i).getId_Projetista();
            row[1] = users.get(i).getNome();
            row[2] = users.get(i).getEspecialidade();
            row[3] = users.get(i).getTelefone();
            row[4] = users.get(i).getEmail();
            row[5] = users.get(i).getContrato();

            model.addRow(row);
        }
        PTABELA.setModel(model);
    }

    public void pesquisadeiro2() {
        
        ArrayList<User2> users2 = ListUsers2(nome2.getText(),telefone2.getText(),email2.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID", "NOME", "TELEFONE", "E-MAIL"});
        Object[] row = new Object[4];

        for (int i = 0; i < users2.size(); i++) {
            row[0] = users2.get(i).getId_Arquiteto();
            row[1] = users2.get(i).getNome();
            row[2] = users2.get(i).getTelefone();
            row[3] = users2.get(i).getEmail();
            model.addRow(row);
        }
        PTABELA2.setModel(model);
    }
    
    
    
    public Connection getConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj_pratico", "root", null);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return con;
    }

    public Pesquisa() {
    initComponents();
  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jFrame1 = new javax.swing.JFrame();
        jButton2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PAINEL_PROJ = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        especialidade = new javax.swing.JTextField();
        nome = new javax.swing.JTextField();
        jRadioButton2 = new javax.swing.JRadioButton();
        telefone = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        email = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        PTABELA = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        NOME_P = new javax.swing.JLabel();
        ESPECIALIDADE_P = new javax.swing.JLabel();
        TELEFONE_P = new javax.swing.JLabel();
        EMAIL_P = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PAINEL_ARQ = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        PTABELA2 = new javax.swing.JTable();
        nome2 = new javax.swing.JTextField();
        telefone2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        NOME_P1 = new javax.swing.JLabel();
        TELEFONE_P1 = new javax.swing.JLabel();
        EMAIL_P1 = new javax.swing.JLabel();
        email2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jButton2.setBackground(new java.awt.Color(255, 153, 153));
        jButton2.setText("REMOVER");

        jButton8.setBackground(new java.awt.Color(204, 255, 204));
        jButton8.setText("ALTERAR");

        jButton11.setBackground(new java.awt.Color(204, 255, 204));
        jButton11.setText("ALTERAR");

        jButton12.setBackground(new java.awt.Color(255, 153, 153));
        jButton12.setText("REMOVER");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("GEPRO SYSTEM");

        PAINEL_PROJ.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        PAINEL_PROJ.setPreferredSize(new java.awt.Dimension(400, 274));

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("terceirizado");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("interno");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("PREENCHA OS CAMPOS QUE DESEJA INCLUIR NA PESQUISA");

        PTABELA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "ESPECIALIDADE", "TELEFONE", "EMAIL", "CONTRATO"
            }
        ));
        jScrollPane4.setViewportView(PTABELA);
        if (PTABELA.getColumnModel().getColumnCount() > 0) {
            PTABELA.getColumnModel().getColumn(0).setResizable(false);
            PTABELA.getColumnModel().getColumn(0).setPreferredWidth(10);
            PTABELA.getColumnModel().getColumn(1).setPreferredWidth(75);
            PTABELA.getColumnModel().getColumn(2).setPreferredWidth(90);
            PTABELA.getColumnModel().getColumn(2).setHeaderValue("ESPECIALIDADE");
            PTABELA.getColumnModel().getColumn(3).setPreferredWidth(80);
            PTABELA.getColumnModel().getColumn(4).setPreferredWidth(50);
            PTABELA.getColumnModel().getColumn(5).setHeaderValue("CONTRATO");
        }

        jLabel4.setText("*(SELECIONE UM REGISTRO E CLIQUE EM REMOVER OU ALTERAR PARA FAZER MODIFICAÇÕES.)");

        jButton5.setBackground(new java.awt.Color(255, 255, 204));
        jButton5.setText("ADICIONAR");
        jButton5.setBorder(null);
        jButton5.setMaximumSize(new java.awt.Dimension(77, 23));
        jButton5.setMinimumSize(new java.awt.Dimension(77, 23));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(204, 255, 204));
        jButton13.setText("ALTERAR");
        jButton13.setBorder(null);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(255, 153, 153));
        jButton14.setText("REMOVER");
        jButton14.setBorder(null);
        jButton14.setMaximumSize(new java.awt.Dimension(77, 23));
        jButton14.setMinimumSize(new java.awt.Dimension(77, 23));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("ESCOLHA A AÇÃO:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton3.setBackground(new java.awt.Color(153, 255, 255));
        jButton3.setText("PESQUISAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        NOME_P.setText("nome:");

        ESPECIALIDADE_P.setText("especialidade:");

        TELEFONE_P.setText("telefone:");

        EMAIL_P.setText("email:");

        jLabel6.setText("contrato:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NOME_P, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
            .addComponent(ESPECIALIDADE_P)
            .addComponent(TELEFONE_P, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(EMAIL_P, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(NOME_P, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ESPECIALIDADE_P, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TELEFONE_P, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EMAIL_P, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PAINEL_PROJLayout = new javax.swing.GroupLayout(PAINEL_PROJ);
        PAINEL_PROJ.setLayout(PAINEL_PROJLayout);
        PAINEL_PROJLayout.setHorizontalGroup(
            PAINEL_PROJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PAINEL_PROJLayout.createSequentialGroup()
                .addGroup(PAINEL_PROJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PAINEL_PROJLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1))
                    .addGroup(PAINEL_PROJLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PAINEL_PROJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PAINEL_PROJLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PAINEL_PROJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(especialidade, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(PAINEL_PROJLayout.createSequentialGroup()
                                    .addComponent(jRadioButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jRadioButton2))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(PAINEL_PROJLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PAINEL_PROJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(PAINEL_PROJLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PAINEL_PROJLayout.setVerticalGroup(
            PAINEL_PROJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PAINEL_PROJLayout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PAINEL_PROJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PAINEL_PROJLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(PAINEL_PROJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PAINEL_PROJLayout.createSequentialGroup()
                                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(especialidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addGroup(PAINEL_PROJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jRadioButton1)
                                    .addComponent(jRadioButton2)))))
                    .addGroup(PAINEL_PROJLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("PROJETISTA", PAINEL_PROJ);

        PTABELA2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "TELEFONE", "EMAIL"
            }
        ));
        jScrollPane6.setViewportView(PTABELA2);
        if (PTABELA2.getColumnModel().getColumnCount() > 0) {
            PTABELA2.getColumnModel().getColumn(0).setResizable(false);
            PTABELA2.getColumnModel().getColumn(0).setPreferredWidth(10);
            PTABELA2.getColumnModel().getColumn(1).setPreferredWidth(75);
            PTABELA2.getColumnModel().getColumn(2).setPreferredWidth(80);
            PTABELA2.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        nome2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nome2ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 204));
        jButton6.setText("ADICIONAR");
        jButton6.setBorder(null);
        jButton6.setMaximumSize(new java.awt.Dimension(77, 23));
        jButton6.setMinimumSize(new java.awt.Dimension(77, 23));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(204, 255, 204));
        jButton15.setText("ALTERAR");
        jButton15.setBorder(null);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(255, 153, 153));
        jButton16.setText("REMOVER");
        jButton16.setBorder(null);
        jButton16.setMaximumSize(new java.awt.Dimension(77, 23));
        jButton16.setMinimumSize(new java.awt.Dimension(77, 23));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("ESCOLHA A AÇÃO:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        NOME_P1.setText("nome:");

        TELEFONE_P1.setText("telefone:");

        EMAIL_P1.setText("email:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NOME_P1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TELEFONE_P1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EMAIL_P1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(NOME_P1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TELEFONE_P1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EMAIL_P1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("PREENCHA OS CAMPOS QUE DESEJA INCLUIR NA PESQUISA");

        jButton7.setBackground(new java.awt.Color(153, 255, 255));
        jButton7.setText("PESQUISAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel11.setText("*(SELECIONE UM REGISTRO E CLIQUE EM REMOVER OU ALTERAR PARA FAZER MODIFICAÇÕES.)");

        javax.swing.GroupLayout PAINEL_ARQLayout = new javax.swing.GroupLayout(PAINEL_ARQ);
        PAINEL_ARQ.setLayout(PAINEL_ARQLayout);
        PAINEL_ARQLayout.setHorizontalGroup(
            PAINEL_ARQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PAINEL_ARQLayout.createSequentialGroup()
                .addGroup(PAINEL_ARQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PAINEL_ARQLayout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addGroup(PAINEL_ARQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nome2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PAINEL_ARQLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11)
                    .addGroup(PAINEL_ARQLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(PAINEL_ARQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PAINEL_ARQLayout.createSequentialGroup()
                    .addGroup(PAINEL_ARQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PAINEL_ARQLayout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(jLabel10))
                        .addGroup(PAINEL_ARQLayout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33)))
        );
        PAINEL_ARQLayout.setVerticalGroup(
            PAINEL_ARQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PAINEL_ARQLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(nome2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(telefone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(email2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(23, 23, 23))
            .addGroup(PAINEL_ARQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PAINEL_ARQLayout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addGroup(PAINEL_ARQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PAINEL_ARQLayout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(238, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("ARQUITETO", PAINEL_ARQ);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        pesquisadeiro();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try{
            remove();
            pesquisadeiro();
        }
        catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Clique em pesquisar para encontrar registros e depois selecione um registro para remover");
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

        try{
            int row = PTABELA.getSelectedRow();
            String campo0 = PTABELA.getModel().getValueAt(row, 0).toString();
            String campo1 = PTABELA.getModel().getValueAt(row, 1).toString();
            String campo2 = PTABELA.getModel().getValueAt(row, 2).toString();
            String campo3 = PTABELA.getModel().getValueAt(row, 3).toString();
            String campo4 = PTABELA.getModel().getValueAt(row, 4).toString();
            String campo5 = PTABELA.getModel().getValueAt(row, 5).toString();
            new Alteradeiro(campo0, campo1, campo2, campo3, campo4, campo5).setVisible(true);
            pesquisadeiro();
        }
        catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Clique em pesquisar para encontrar registros e depois selecione um registro para alterar");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         new AdicaoProj().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new AdicaoArq().setVisible(true);
        dispose();       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
     
        try{
            int row = PTABELA2.getSelectedRow();
            String campo0 = PTABELA2.getModel().getValueAt(row, 0).toString();
            String campo1 = PTABELA2.getModel().getValueAt(row, 1).toString();
            String campo2 = PTABELA2.getModel().getValueAt(row, 2).toString();
            String campo3 = PTABELA2.getModel().getValueAt(row, 3).toString();         
            new Alteradeiroarq(campo0, campo1, campo2, campo3).setVisible(true);
            pesquisadeiro2();
        }
        catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Clique em pesquisar para encontrar registros e depois selecione um registro para alterar");
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
       try{
            remove2();
            pesquisadeiro2();
        }
        catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Clique em pesquisar para encontrar registros e depois selecione um registro para remover");
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       pesquisadeiro2();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void nome2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome2ActionPerformed

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
            java.util.logging.Logger.getLogger(Pesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pesquisa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EMAIL_P;
    private javax.swing.JLabel EMAIL_P1;
    private javax.swing.JLabel ESPECIALIDADE_P;
    private javax.swing.JLabel NOME_P;
    private javax.swing.JLabel NOME_P1;
    private javax.swing.JPanel PAINEL_ARQ;
    private javax.swing.JPanel PAINEL_PROJ;
    private javax.swing.JTable PTABELA;
    private javax.swing.JTable PTABELA2;
    private javax.swing.JLabel TELEFONE_P;
    private javax.swing.JLabel TELEFONE_P1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField email;
    private javax.swing.JTextField email2;
    private javax.swing.JTextField especialidade;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField nome2;
    private javax.swing.JTextField telefone;
    private javax.swing.JTextField telefone2;
    // End of variables declaration//GEN-END:variables
}
