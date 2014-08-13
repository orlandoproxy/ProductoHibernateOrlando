/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;
import cl.model.pojos.Producto;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.util.List;

/**
 *
 * @author orlandoproxy
 */
public class ProductoDAO
{
    public void ingresarProducto(Producto p)
    {
        SessionFactory sf=null;
        Session sesion=null;
        Transaction tx = null;
        try {
            sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            tx= sesion.beginTransaction();
            sesion.save(p);
            tx.commit();
            sesion.close();
        } catch (Exception ex)
        {
            tx.rollback();
            throw new RuntimeException("Nose puede guardar");
            
        }
    }
    public String consultarProducto(int codigo)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Producto p = (Producto)sesion.get(Producto.class, codigo);
        sesion.close();
        if(p!=null)
        {
            return "el producto del codigo "+p.getCodigo()+"cuyo nombre es "+p.getNombre()+"cuesta "+p.getPrecio()+"tiene stock"+p.getStock()+"yu describe"+p.getDescripcion();
        }
        else
        {
            return "el producto de codigo "+codigo+" no esciste";
        }
    }
    public List<Producto> verProductos()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.getCurrentSession();
        Query query  = sesion.createQuery("from Producto");
        List<Producto> lista = query.list();
        return lista;
    }
    
}
