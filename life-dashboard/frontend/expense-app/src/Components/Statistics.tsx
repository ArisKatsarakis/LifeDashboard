import { Container } from "react-bootstrap";
import { useState, useEffect } from "react";
import { ExpenseStat, ExpenseType } from "../Interfaces/ExpenseInterfaces";
import { variables } from "../Utilities/Variables";
import axios from "axios";
import { Accordion, AccordionSummary, AccordionDetails, Paper, Grid, Box, Chip } from "@mui/material";
import ArrowDownwardIcon from '@mui/icons-material/ArrowDownward';
import { EuroSharp, EuroSymbol } from "@mui/icons-material";
import { styled } from '@mui/material/styles';
import { Calendar } from "../Icons/CommonIcons";

export function Statistiscs() {
  const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  }));
  const [expenseStats, setExpenseStats] = useState<ExpenseType[]>([]);

  const fetchExpensesStats = async () => {
    const stats: ExpenseStat = await axios.get(`${variables.fetchExpenseStats}`);
    setExpenseStats(stats.data);
  };
  useEffect(
    () => {
      fetchExpensesStats();
    }, []
  );
  return (
    <Container>
      <h2> Statistiscs </h2>
      <Chip label={'Expenses'} />
      {
        expenseStats.map(
          stat => {
            return (
              <Accordion id={stat.expenseType}>
                <AccordionSummary expandIcon={<ArrowDownwardIcon />}>
                  {stat.expenseType}
                </AccordionSummary>
                <AccordionDetails>

                  <Box sx={{ flexGrow: 1 }}>
                    <Grid container spacing={2}>
                      {stat.expenses.map(
                        expense => {
                          return (
                            <Grid item xs={4}>
                              <Item>{expense.moneySpent} <EuroSharp /></Item>
                              <Item> {expense.dateCreated} <Calendar /></Item>
                            </Grid>
                          )
                        })}
                      <Grid item xs={4}>
                      </Grid>
                    </Grid>
                  </Box>
                </AccordionDetails>
              </Accordion>
            )
          }
        )
      }
      <Container>
        <h3 style={{ background: 'green' }}>Incomes</h3>
      </Container>
    </Container >
  );
}
