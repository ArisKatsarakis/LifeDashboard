export function ExpenseAmmount(props: { expensesSum: number }) {
  return (
    <span style={{ border: '1px solid black', marginRight: '1rem', textAlign: 'center', padding: '5px', fontSize: '30px', borderRadius: '1rem' }}>
      Money Spent ${props.expensesSum}
    </span>
  )
}
