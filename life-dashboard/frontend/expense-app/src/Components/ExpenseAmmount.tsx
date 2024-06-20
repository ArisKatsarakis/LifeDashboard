export function ExpenseAmmount(props: { expensesSum: number }) {
  return (
    <div style={{ textAlign: 'center', marginBottom: '1rem' }}>
      <span style={{ border: '1px solid black', marginTop: '1rem', textAlign: 'center', padding: '5px', fontSize: '30px', borderRadius: '1rem' }}>
        Money Spent ${props.expensesSum}
      </span>
    </div>
  )
}
