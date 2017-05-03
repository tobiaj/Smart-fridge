package Database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by tobiaj on 2017-04-26.
 */
public class DatabaseHandler implements DatabaseInterface{
    private EntityManagerFactory emFactory;
    private EntityManagerFactory emFactoryEAN;


    public DatabaseHandler(){
        super();
        emFactory = Persistence.createEntityManagerFactory("Food");
        emFactoryEAN = Persistence.createEntityManagerFactory("EAN");

    }


    @Override
    public void add(int barcode) {
        EntityManager em = beginTransaction();
        try{

            Products temp = em.createNamedQuery("get", Products.class)
                    .setParameter("barcode", barcode)
                    .getSingleResult();

            if (temp != null) {

                int tempQuantity = temp.getQuantity();
                tempQuantity = tempQuantity + 1;

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                String s = timestamp.toString().split("\\.")[0];

                temp.setQuantity(tempQuantity);

                temp.setUpdated_at(Timestamp.valueOf(s));

                em.persist(temp);
            }
            else {
                EANProducts eanProducts = getEANNumber(barcode);

                int eanNumber = eanProducts.getId();

                String name = eanProducts.getName();

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                String s = timestamp.toString().split("\\.")[0];

                Timestamp timestamp1 = Timestamp.valueOf(s);

                Products products = new Products(eanNumber, name, 1, timestamp1, timestamp1);

                em.persist(products);

            }

        }finally
        {
            commitTransaction(em);
        }


    }

    private EANProducts getEANNumber(int barcode) {
        EntityManager EANem = beginTransactionEAN();
        try{

            EANProducts temp = EANem.createNamedQuery("get", EANProducts.class)
                    .setParameter("barcode", barcode)
                    .getSingleResult();

            return temp;

        }finally
        {
            commitTransaction(EANem);
        }

    }

    @Override
    public void remove(int barcode) {
        EntityManager em = beginTransaction();
        try{

            Products temp = em.createNamedQuery("get", Products.class)
                    .setParameter("barcode", barcode)
                    .getSingleResult();

            int tempQuantity = temp.getQuantity();
            if (tempQuantity != 0){
                tempQuantity = tempQuantity - 1;
            }

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            String s = timestamp.toString().split("\\.")[0];

            temp.setQuantity(tempQuantity);

            temp.setUpdated_at(Timestamp.valueOf(s));

            em.persist(temp);

        }finally
        {
            commitTransaction(em);
        }


    }


    private synchronized EntityManager beginTransaction()
    {
        EntityManager em = emFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        return em;
    }


    private synchronized EntityManager beginTransactionEAN()
    {
        EntityManager EANem = emFactoryEAN.createEntityManager();
        EntityTransaction transaction = EANem.getTransaction();
        transaction.begin();
        return EANem;
    }

    private synchronized void commitTransaction(EntityManager em)
    {
        em.getTransaction().commit();
    }
}
