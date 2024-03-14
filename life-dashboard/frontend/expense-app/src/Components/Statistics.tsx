import { Container } from "react-bootstrap";
import { useState, useEffect } from "react";
import { ExpenseStat, ExpenseType } from "../Interfaces/ExpenseInterfaces";
import { variables } from "../Utilities/Variables";
import axios from "axios";
import { Accordion, AccordionSummary, AccordionDetails, Paper, Grid, Box, Chip, Divider, ButtonBase, Button } from "@mui/material";
import ArrowDownwardIcon from '@mui/icons-material/ArrowDownward';
import { EuroSharp, EuroSymbol } from "@mui/icons-material";
import { styled } from '@mui/material/styles';
import { Calendar } from "../Icons/CommonIcons";
import { GridPanelHeader } from "@mui/x-data-grid";
import { IncomeStatsPromise, IncomeStats } from "../Interfaces/IncomeInterfaces";
export function Statistiscs() {
  const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  }));
  const [expenseStats, setExpenseStats] = useState<ExpenseType[]>([]);
  const [incomeStats, setIncomeStats] = useState<IncomeStats[]>([]);

  const fetchExpensesStats = async () => {
    const stats: ExpenseStat = await axios.get(`${variables.fetchExpenseStats}`);
    setExpenseStats(stats.data);
  };

  const fetchIncomeStats = async () => {
    const stats: IncomeStatsPromise = await axios.get(`${variables.fetchIncomesURL}/stats`);
    setIncomeStats(stats.data);
  }
  useEffect(
    () => {
      fetchExpensesStats();
      fetchIncomeStats();
    }, []
  );
  return (
    <Container>
      <h2> Statistiscs </h2>
      <Chip label={'Expenses'} style={{ color: 'red' }} />
      <ButtonBase>
        <Button variant='outlined'> Today </Button>
        <Button variant='outlined'> Week </Button>
        <Button variant='outlined'> Month </Button>
      </ButtonBase>
      <Divider component='legend' />
      {
        expenseStats.map(
          stat => {
            return (
              <Accordion id={stat.expenseType}>
                <AccordionSummary expandIcon={<ArrowDownwardIcon />}>
                  {stat.expenseType} {stat.moneySum} <EuroSharp />
                </AccordionSummary>
                <AccordionDetails>

                  <Box sx={{ flexGrow: 1 }}>
                    <Grid container spacing={2}>
                      {stat.expenses.map(
                        expense => {
                          return (
                            <Grid item xs={4}>
                              <Item>{expense.moneySpent} <EuroSharp /> {expense.dateCreated} <Calendar /></Item>
                            </Grid>
                          )
                        })}
                    </Grid>
                  </Box>
                </AccordionDetails>
              </Accordion>
            )
          }
        )
      }
      <Container style={{ marginTop: '1rem' }}>
        <Chip label={'Expenses'} style={{ color: 'green' }} />
        <ButtonBase>
          <Button variant='outlined'> Today </Button>
          <Button variant='outlined'> Week </Button>
          <Button variant='outlined'> Month </Button>
        </ButtonBase>
        <Divider component='legend' />
        {
          incomeStats.map(
            stat => {
              return (
                <Accordion id={stat.incomeSource}>
                  <AccordionSummary expandIcon={<ArrowDownwardIcon />}>
                    {stat.incomeType} {stat.moneySum} <EuroSharp />
                  </AccordionSummary>
                  <AccordionDetails>

                    <Box sx={{ flexGrow: 1 }}>
                      <Grid container spacing={2}>
                        {stat.incomes.map(
                          income => {
                            return (
                              <Grid item xs={4}>
                                <Item>{income.moneyReceived} <EuroSharp /> {income.dateCreated} <Calendar /></Item>
                              </Grid>
                            )
                          })}
                      </Grid>
                    </Box>
                  </AccordionDetails>
                </Accordion>
              )
            }
          )
        }
      </Container>
    </Container >
  );
}                         
