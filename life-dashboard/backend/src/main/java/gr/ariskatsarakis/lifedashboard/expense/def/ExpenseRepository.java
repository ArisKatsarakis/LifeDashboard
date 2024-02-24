package gr.ariskatsarakis.lifedashboard.expense.def;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Expense.class)
public   interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {
    @Query(value = "select e from Expense  e " +
            "where extract(month from e.dateCreated)  = ?1 ")
    List<Expense> findForMonth(int month);

    @Modifying
    @Query("delete from  Expense e where e.expenseId = ?1")
    void deleteExpenseById(long expenseId);

    @Query( "SELECT ex from Expense ex ORDER BY ex.dateCreated")
    List<Expense> getLast10();
}
