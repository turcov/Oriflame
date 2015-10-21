 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.services;

import com.iucosoft.oriflame.db.MyDataSource;
import com.iucosoft.oriflame.domain.Message;
import com.iucosoft.oriflame.domain.News;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Serguei
 */
public class NewsServiceDaoImpl implements NewsServiceIntf {

    private static NewsServiceDaoImpl instance;
    private MyDataSource myDataSource;

    private Connection conn;

    public static NewsServiceDaoImpl getInstance(MyDataSource myDataSource) {
        if (instance == null) {
            instance = new NewsServiceDaoImpl(myDataSource);
        }
        return instance;
    }

    private NewsServiceDaoImpl(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    private int getLastId(Connection conn) {
        String sql = "SELECT MAX(ID) FROM news";
        int rez = 0;
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                rez = rs.getInt("MAX(ID)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NewsServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NewsServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return rez;
    }

    @Override
    public int addNews(News news) {
        String sql = "INSERT INTO news VALUES (?,?,?,?,?)";
        PreparedStatement pstat = null;
        int id = news.getId();
        try {
            conn = myDataSource.getConnection();
            pstat = conn.prepareStatement(sql);
            if (id == 0) {
                id = getLastId(conn) + 1;
            }
            pstat.setInt(1, id);
            pstat.setString(2, news.getLocale());
            pstat.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
            pstat.setString(4, news.getTitle());
            pstat.setString(5, news.getInfo());
            pstat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NewsServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (pstat != null) {
                    pstat.close();
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
        return id;
    }

    @Override
    public void editNews(News news) {
        String sql = "UPDATE news SET date=?, title=?, info=? WHERE id=? and locale=?";
        PreparedStatement pstat = null;
        try {
            conn = myDataSource.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setTimestamp(1, new java.sql.Timestamp(news.getDate().getTime()));
            pstat.setString(2, news.getTitle());
            pstat.setString(3, news.getInfo());
            pstat.setInt(4, news.getId());
            pstat.setString(5, news.getLocale());
            pstat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(NewsServiceDaoImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pstat != null) {
                    pstat.close();
                }
                myDataSource.closeConnection();

            } catch (SQLException ex) {
                Logger.getLogger(NewsServiceDaoImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void removeNews(int id) {
        String sql = "DELETE FROM news WHERE id=?";
        PreparedStatement pstat = null;
        try {
            conn = myDataSource.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, id);
            pstat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(NewsServiceDaoImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pstat != null) {
                    pstat.close();
                }
                myDataSource.closeConnection();

            } catch (SQLException ex) {
                Logger.getLogger(NewsServiceDaoImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<News> readNewsbyId(int id) {
        String sql = "SELECT * FROM news WHERE id=?";
        PreparedStatement pstat = null;
        ResultSet rs = null;
        News news = null;
        List<News> listNews = new ArrayList<>();
        try {
            conn = myDataSource.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, id);
            rs = pstat.executeQuery();
            while (rs.next()) {
                news = new News();
                news.setId(id);
                news.setLocale(rs.getString(2));
                news.setDate(rs.getTimestamp(3));
                news.setTitle(rs.getString(4));
                news.setInfo(rs.getString(5));
                listNews.add(news);

            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsServiceDaoImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstat != null) {
                    pstat.close();
                }
                myDataSource.closeConnection();

            } catch (SQLException ex) {
                Logger.getLogger(NewsServiceDaoImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listNews;
    }

    @Override
    public News readNewsbyIdLocale(int id, String locale) {
        String sql = "SELECT * FROM news WHERE id=? and locale=?";
        PreparedStatement pstat = null;
        ResultSet rs = null;
        News news = null;
        try {
            conn = myDataSource.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, id);
            pstat.setString(2, locale);
            rs = pstat.executeQuery();
            if (rs.next()) {
                news = new News();
                news.setId(id);
                news.setLocale(locale);
                news.setDate(rs.getTimestamp(3));
                news.setTitle(rs.getString(4));
                news.setInfo(rs.getString(5));

            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsServiceDaoImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstat != null) {
                    pstat.close();
                }
                myDataSource.closeConnection();

            } catch (SQLException ex) {
                Logger.getLogger(NewsServiceDaoImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return news;
    }

    @Override
    public List<News> findAllTitlesNewsByLocale(String locale) {
        String sql = "SELECT * FROM news WHERE locale=? ORDER BY id DESC";
        PreparedStatement pstat = null;
        ResultSet rs = null;
        News news = null;
        List<News> newsList = new ArrayList<>();
        try {
            conn = myDataSource.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, locale);
            rs = pstat.executeQuery();
            while (rs.next()) {
                news = new News();
                news.setId(rs.getInt(1));
                news.setLocale(rs.getString(2));
                news.setDate(rs.getTimestamp(3));
                news.setTitle(rs.getString(4));
                newsList.add(news);

            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsServiceDaoImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstat != null) {
                    pstat.close();
                }
                myDataSource.closeConnection();

            } catch (SQLException ex) {
                Logger.getLogger(NewsServiceDaoImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return newsList;
    }

    @Override
    public List<News> findAllNewsByLocale(String locale) {
        String sql = "SELECT * FROM news WHERE locale=? ORDER BY id DESC";
        PreparedStatement pstat = null;
        ResultSet rs = null;
        News news = null;
        List<News> newsList = new ArrayList<>();
        try {
            conn = myDataSource.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, locale);
            rs = pstat.executeQuery();
            while (rs.next()) {
                news = new News();
                news.setId(rs.getInt(1));
                news.setLocale(rs.getString(2));
                news.setDate(rs.getTimestamp(3));
                news.setTitle(rs.getString(4));
                news.setInfo(rs.getString(5));
                newsList.add(news);

            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsServiceDaoImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstat != null) {
                    pstat.close();
                }
                myDataSource.closeConnection();

            } catch (SQLException ex) {
                Logger.getLogger(NewsServiceDaoImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return newsList;
    }

    @Override
    public List<String> findAllLocales() {
        String sql = "SELECT DISTINCT locale FROM news";
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
            Logger.getLogger(NewsServiceDaoImpl.class
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
                Logger.getLogger(NewsServiceDaoImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listLocales;
    }

}
