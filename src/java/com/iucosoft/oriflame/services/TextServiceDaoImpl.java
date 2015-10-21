/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.services;

import com.iucosoft.oriflame.db.MyDataSource;
import com.iucosoft.oriflame.domain.PageText;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iurasun
 */
public class TextServiceDaoImpl implements TextServiceIntf {

    //Sigleton
    private static TextServiceDaoImpl instance;

    public static TextServiceDaoImpl getInstance(MyDataSource myDataSource) {

        if (instance == null) {
            instance = new TextServiceDaoImpl(myDataSource);
        }
        return instance;
    }

    private MyDataSource myDataSource;

    private Connection conn;

    private TextServiceDaoImpl(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    @Override
    public void save(PageText pt) {
        Statement stat = null;
        try {
            conn = myDataSource.getConnection();

            String sql = "UPDATE text SET pagetext='" + pt.getText() + "' WHERE pagename='" + pt.getPageName() + "' and locale='" + pt.getLocale() + "'";

            stat = conn.createStatement();
            stat.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(TextServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TextServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                myDataSource.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TextServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public List<PageText> findByPageName(String pageName) {
        PageText pageText = null;
        ResultSet rs = null;
        Statement stat = null;
        List<PageText> listPages = new ArrayList<>();
        try {
            conn = myDataSource.getConnection();

            String sql = "SELECT * FROM text WHERE pagename='" + pageName + "'";

            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {

                int id = rs.getInt(1);
                String pageTextStr = rs.getString(3);
                String locale = rs.getString(4);

                pageText = new PageText(id, pageName, pageTextStr, locale);
                listPages.add(pageText);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TextServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TextServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TextServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                myDataSource.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TextServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listPages;

    }

    @Override
    public PageText findByPageName(String pageName, String locale) {
        PageText pageText = null;
        ResultSet rs = null;
        Statement stat = null;
        try {
            conn = myDataSource.getConnection();

            String sql = "SELECT * FROM text WHERE pagename='" + pageName + "' and locale='" + locale + "'";

            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            if (rs.next()) {

                int id = rs.getInt(1);
                String pageTextStr = rs.getString(3);

                pageText = new PageText(id, pageName, pageTextStr, locale);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TextServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TextServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TextServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                myDataSource.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(TextServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pageText;

    }

    @Override
    public List<String> findAllLocales() {
        String sql = "SELECT DISTINCT locale FROM text";
        Statement stat = null;
        ResultSet rs = null;
        List<String> listLocales = new ArrayList<>();
        try {
            conn = myDataSource.getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                listLocales.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TextServiceDaoImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stat != null) {
                    stat.close();
                }
                myDataSource.closeConnection();

            } catch (SQLException ex) {
                Logger.getLogger(TextServiceDaoImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listLocales;
    }
}
