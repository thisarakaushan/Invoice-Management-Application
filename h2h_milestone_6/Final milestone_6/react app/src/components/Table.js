import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Checkbox from '@material-ui/core/Checkbox';
import Paper from '@material-ui/core/Paper';

const useStyles = makeStyles({
  table: {
    minWidth: 650,
  },
});

const MyTable = () => {
  const classes = useStyles();
  const [tableData, setTableData] = useState([]);
  const [selectedRows, setSelectedRows] = useState([]);
  const [selectAll, setSelectAll] = useState(false);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await fetch('http://localhost:8081/first1/data_loading');
      const data = await response.json();
      setTableData(data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  const handleCheckboxChange = (event, row) => {
    const selectedRowIds = [...selectedRows];
    if (event.target.checked) {
      selectedRowIds.push(row.customerOrderId);
    } else {
      const index = selectedRowIds.indexOf(row.customerOrderId);
      if (index > -1) {
        selectedRowIds.splice(index, 1);
      }
    }
    setSelectedRows(selectedRowIds);
  };

  const handleSelectAllChange = (event) => {
    const isChecked = event.target.checked;
    const selectedRowIds = isChecked
      ? tableData.map((row) => row.customerOrderId)
      : [];

    setSelectedRows(selectedRowIds);
    setSelectAll(isChecked);
  };

  const columns = [
    { field: 'slNo', label: 'Sl No' },
    { field: 'customerOrderId', label: 'Customer Order Id' },
    { field: 'salesOrg', label: 'Sales Org' },
    { field: 'distributionChannel', label: 'Distribution Channel' },
    { field: 'companyCode', label: 'Company Code' },
    { field: 'orderCreationDate', label: 'Order Creation Date' },
    { field: 'orderCurrency', label: 'Order Currency' },
    { field: 'customerNumber', label: 'Customer Number' },
    { field: 'amountInUSD', label: 'Amount in USD' },
    { field: 'orderAmount', label: 'Order Amount' },
  ];

  return (
    <TableContainer component={Paper}>
      <Table className={classes.table} aria-label="My Table">
        <TableHead>
          <TableRow>
            <TableCell>
              <Checkbox
                checked={selectAll}
                onChange={handleSelectAllChange}
              />
            </TableCell>
            {columns.map((column) => (
              <TableCell key={column.field}>{column.label}</TableCell>
            ))}
          </TableRow>
        </TableHead>
        <TableBody>
          {tableData.map((row, index) => (
            <TableRow key={index}>
              <TableCell>
                <Checkbox
                  checked={selectedRows.includes(row.customerOrderId)}
                  onChange={(event) => handleCheckboxChange(event, row)}
                />
              </TableCell>
              {columns.map((column) => (
                <TableCell key={column.field}>{row[column.field]}</TableCell>
              ))}
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default MyTable;
