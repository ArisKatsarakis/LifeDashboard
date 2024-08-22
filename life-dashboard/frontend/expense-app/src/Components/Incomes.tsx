import { Income } from "../interfaces/IncomeInterfaces";

export function Incomes(props: { items: Income[] }) {
  return (
    <div className="container">
      <h2>INCOMES</h2>
      <div className="row">
        {props.items.map(
          item => {
            return (
              <div key={item.incomeId} style={{ textAlign: 'center', border: '1px solid black', margin: '1rem' }} className={'col'}>
                <span style={{ margin: '1rem', fontSize: '30px' }}>Stream: {item.incomeType}</span>
                <hr />
                <span style={{ fontSize: '30px', backgroundColor: 'gray' }}>$ {item.money}</span>
              </div>
            )
          }
        )}
      </div>
    </div>
  )
}
