export function ExpenseAmmount(props: { expensesSum: number }) {
  return (
    <div style={{ textAlign: 'center', border: '1px solid black', margin: '1rem', boxShadow: '2px 1px 11px 0px', backgroundColor: 'gold' }} className={'col'}>
      <span style={{ margin: '1rem', fontSize: '30px' }}>
        Money Spent ${props.expensesSum}
      </span>
    </div>
  )
}
