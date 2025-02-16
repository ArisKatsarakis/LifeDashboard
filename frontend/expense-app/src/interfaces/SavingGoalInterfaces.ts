export interface SavingGoal {
  savingGoalId: number,
  target: number,
  startDate: string,
  endDate: string,
  dailyGoals: [DailyExpenseGoal]
}

export interface DailyExpenseGoal {
  dailyExpenseGoalId: number,
  regardingDay: string,
  status: string,
  moneyGoal: number
}
