package gr.ariskatsarakis.lifedashboard.expense.def;

import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseType;
import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Expense.class)
public   interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {
    //Empty
    @Query(value = "select e from Expense  e " +
            "where extract(month from e.dateCreated)  = ?1 ")
    List<Expense> findForMonth(int month);
}
