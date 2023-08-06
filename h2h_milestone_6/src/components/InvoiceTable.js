// import React, { useEffect, useState } from 'react';
// import axios from 'axios';
// import {
//   TableContainer,
//   Table,
//   TableHead,
//   TableBody,
//   TableRow,
//   TableCell,
//   Paper,
//   styled,
//   TablePagination,
//   ThemeProvider,
//   Box,
//   Checkbox, // Import Checkbox from Material-UI
// } from '@mui/material';
// import { theme } from '../theme'; // Import the theme
// // import { DataGrid } from '@mui/x-data-grid';
// import { lighten, makeStyles } from '@material-ui/core/styles';
// import TableSortLabel from '@material-ui/core/TableSortLabel';



// const InvoiceMuiTable = () => {
//   const useStyles = makeStyles((theme) => ({
//     root: {
//       width: '100%',
//     },
//     paper: {
//       width: '100%',
//       marginBottom: theme.spacing(2),
//     },
//     table: {
//       minWidth: 750,
//     },
//     visuallyHidden: {
//       border: 0,
//       clip: 'rect(0 0 0 0)',
//       height: 1,
//       margin: -1,
//       overflow: 'hidden',
//       padding: 0,
//       position: 'absolute',
//       top: 20,
//       width: 1,
//     },
//   }));



//   var numSelected;
//   var rowCount;
//   var onSelectAllClick;
//   var onRequestSort;


//   const [order, setOrder] = React.useState('asc');
//   const [orderBy, setOrderBy] = React.useState('slNo');
//   const classes = useStyles();
//   const [data, setData] = useState([]);
//   const [page, setPage] = useState(0);
//   const [rowsPerPage, setRowsPerPage] = useState(10);
//   const [total, setTotal] = useState(0);
//   const [selectAll, setSelectAll] = useState(false);
//   const [selected, setSelected] = React.useState([]);

//   const handleSelectAllClick = (event) => {
//     if (event.target.checked) {
//       const newSelecteds = rows.map((n) => n.name);
//       setSelected(newSelecteds);
//       return;
//     }
//     setSelected([]);
//   };

//   const handleRequestSort = (event, property) => {
//     const isAsc = orderBy === property && order === 'asc';
//     setOrder(isAsc ? 'desc' : 'asc');
//     setOrderBy(property);
//   };

//   const createSortHandler = (property) => (event) => {
//     onRequestSort(event, property);
//   };


//   //creating the column
//   const columns = [
//     { field: 'slNo', headerName: 'Sl no', width: 100, headerClassName: 'header-wrap' },
//     { field: 'customerOrderId', headerName: 'CUSTOMER ORDER ID', width: 150, headerClassName: 'header-wrap' },
//     { field: 'salesOrg', headerName: 'SALES ORG', width: 120 },
//     { field: 'distributionChannel', headerName: 'DISTRIBUTION CHANNEL', width: 180 },
//     { field: 'companyCode', headerName: 'COMPANY CODE', width: 120 },
//     { field: 'orderCreationDate', headerName: 'ORDER CREATION DATE', width: 180 },
//     { field: 'orderAmount', headerName: 'ORDER AMOUNT', width: 140 },
//     { field: 'orderCurrency', headerName: 'ORDER CURRENCY', width: 140 },
//     { field: 'customerNumber', headerName: 'CUSTOMER NUMBER', width: 140 },
//     { field: 'amountInUSD', headerName: 'AMOUNT IN USD', width: 140 },
//   ];

//   useEffect(() => {
//     fetchData();
//   }, []);

//   const fetchData = async () => {
//     try {
//       const response = await axios.get('http://localhost:8081/first1/data_loading');
//       setData(response.data);
//       setTotal(response.data.length);
//     } catch (error) {
//       console.error('Error fetching data:', error);
//     }
//   };

//   const handleChangePage = (event, newPage) => {
//     setPage(newPage);
//   };

//   const rows = data.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((item) => ({
//     id: item.slNo,
//     ...item,
//   }));


  




//   return (
//     <ThemeProvider theme={theme}>
//       <Box sx={{ width: '100%' }}>
//         <Paper elevation={3} sx={{ width: '100%' }}>
//           <TableContainer component={Paper}>
//             <Table aria-label="simple table">
//               <TableHead
//                 // classes={classes}
//                 numSelected={selected.length}
//                 order={order}
//                 orderBy={orderBy}
//                 onSelectAllClick={handleSelectAllClick}
//                 onRequestSort={handleRequestSort}
//                 rowCount={rows.length}
//               >
//                 <TableRow>
//                   <TableCell padding="checkbox">
//                     <Checkbox
//                       indeterminate={numSelected > 0 && numSelected < rowCount}
//                       checked={rowCount > 0 && numSelected === rowCount}
//                       onChange={onSelectAllClick}
//                       inputProps={{ 'aria-label': 'select all desserts' }}
//                     />
//                   </TableCell>
//                   {columns.map((columns) => (
//                     <TableCell
//                       key={columns.field}
//                       align={columns.numeric ? 'right' : 'left'}
//                       padding={columns.disablePadding ? 'none' : 'normal'}
//                       sortDirection={orderBy === columns.field ? order : false}
//                     >
//                       <TableSortLabel
//                         active={orderBy === columns.field}
//                         direction={orderBy === columns.field ? order : 'asc'}
//                         onClick={createSortHandler(columns.field)}
//                       >
//                         {columns.headerName}
//                         {orderBy === columns.field ? (
//                           <span className={classes.visuallyHidden}>
//                             {order === 'desc' ? 'sorted descending' : 'sorted ascending'}
//                           </span>
//                         ) : null}
//                       </TableSortLabel>
//                     </TableCell>
//                   ))}
//                 </TableRow>
//               </TableHead>
//               <TableBody>
//                 {rows.map((row) => (
//                   <TableRow key={row.id}>
//                     {columns.map((column) => (
//                       <TableCell key={`${row.id}-${column.field}`}>
//                         {column.field === 'checkbox' ? (
//                           <Checkbox />
//                         ) : (
//                           row[column.field]
//                         )}
//                       </TableCell>
//                     ))}
//                   </TableRow>
//                 ))}
//               </TableBody>
//             </Table>
//           </TableContainer>
//           <TablePagination
//             rowsPerPageOptions={[5, 8, 10, 20, 50, 100]}
//             component="div"
//             count={total}
//             rowsPerPage={rowsPerPage}
//             page={page}
//             onPageChange={handleChangePage}
//             onRowsPerPageChange={handleChangeRowsPerPage}
//           />
//         </Paper>
//       </Box>
//     </ThemeProvider>
//   );


//   //functionalities
//   function descendingComparator(a, b, orderBy) {
//     if (b[orderBy] < a[orderBy]) {
//       return -1;
//     }
//     if (b[orderBy] > a[orderBy]) {
//       return 1;
//     }
//     return 0;
//   }

//   function getComparator(order, orderBy) {
//     return order === 'desc'
//       ? (a, b) => descendingComparator(a, b, orderBy)
//       : (a, b) => -descendingComparator(a, b, orderBy);
//   }

//   function stableSort(array, comparator) {
//     const stabilizedThis = array.map((el, index) => [el, index]);
//     stabilizedThis.sort((a, b) => {
//       const order = comparator(a[0], b[0]);
//       if (order !== 0) return order;
//       return a[1] - b[1];
//     });
//     return stabilizedThis.map((el) => el[0]);
//   }

//   const handleChangeRowsPerPage = (event) => {
//     setRowsPerPage(parseInt(event.target.value, 10));
//     setPage(0);
//   };

//   //   const handleSelectAll = (event) => {
//   //     setSelectAll(event.target.checked);
//   //   };
//   const handleSelectAll = (event) => {
//     const checked = event.target.checked;
//     setSelectAll(checked);
//     const updatedData = data.map((item) => ({
//       ...item,
//       checkbox: checked,
//     }));
//     setData(updatedData);
//   };

// };

// export default InvoiceMuiTable;
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
  TableContainer,
  Table,
  TableHead,
  TableBody,
  TableRow,
  TableCell,
  Paper,
  styled,
  TablePagination,
  ThemeProvider,
  Box,
  Checkbox,
} from '@mui/material';
import { theme } from '../theme';
import { lighten, makeStyles } from '@material-ui/core/styles';
import TableSortLabel from '@material-ui/core/TableSortLabel';

const InvoiceMuiTable = () => {
  const useStyles = makeStyles((theme) => ({
    root: {
      width: '100%',
    },
    paper: {
      width: '100%',
      marginBottom: theme.spacing(2),
    },
    table: {
      minWidth: 750,
    },
    visuallyHidden: {
      border: 0,
      clip: 'rect(0 0 0 0)',
      height: 1,
      margin: -1,
      overflow: 'hidden',
      padding: 0,
      position: 'absolute',
      top: 20,
      width: 1,
    },
  }));

  const classes = useStyles();
  const [orderBy, setOrderBy] = useState(''); // Add this line
  const [order, setOrder] = useState('asc'); // Add this line
  const [data, setData] = useState([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [total, setTotal] = useState(0);
  const [selectAll, setSelectAll] = useState(false);
  const [selected, setSelected] = useState([]);

  const handleSelectAllClick = (event) => {
    if (event.target.checked) {
      const newSelecteds = rows.map((n) => n.name);
      setSelected(newSelecteds);
    } else {
      setSelected([]);
    }
  };

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === 'asc';
    setOrder(isAsc ? 'desc' : 'asc');
    setOrderBy(property);
  };

  const createSortHandler = (property) => (event) => {
    handleRequestSort(event, property);
  };

  const fetchData = async () => {
    try {
      const response = await axios.get('http://localhost:8081/first1/data_loading');
      setData(response.data);
      setTotal(response.data.length);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const columns = [
    { field: 'slNo', headerName: 'Sl no', width: 100, headerClassName: 'header-wrap' },
    { field: 'customerOrderId', headerName: 'CUSTOMER ORDER ID', width: 150, headerClassName: 'header-wrap' },
    { field: 'salesOrg', headerName: 'SALES ORG', width: 120 },
    { field: 'distributionChannel', headerName: 'DISTRIBUTION CHANNEL', width: 180 },
    { field: 'companyCode', headerName: 'COMPANY CODE', width: 120 },
    { field: 'orderCreationDate', headerName: 'ORDER CREATION DATE', width: 180 },
    { field: 'orderAmount', headerName: 'ORDER AMOUNT', width: 140 },
    { field: 'orderCurrency', headerName: 'ORDER CURRENCY', width: 140 },
    { field: 'customerNumber', headerName: 'CUSTOMER NUMBER', width: 140 },
    { field: 'amountInUSD', headerName: 'AMOUNT IN USD', width: 140 },
  ];

  const rows = data
    .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
    .map((item) => ({
      id: item.slNo,
      ...item,
    }));

  return (
    <ThemeProvider theme={theme}>
      <Box sx={{ width: '100%' }}>
        <Paper elevation={3} sx={{ width: '100%' }}>
          <TableContainer component={Paper}>
            <Table aria-label="simple table">
              <TableHead>
                <TableRow>
                  <TableCell padding="checkbox">
                    <Checkbox
                      indeterminate={selected.length > 0 && selected.length < rows.length}
                      checked={selected.length === rows.length}
                      onChange={handleSelectAllClick}
                      inputProps={{ 'aria-label': 'select all desserts' }}
                    />
                  </TableCell>
                  {columns.map((column) => (
                    <TableCell
                      key={column.field}
                      align={column.numeric ? 'right' : 'left'}
                      padding={column.disablePadding ? 'none' : 'normal'}
                    >
                      <TableSortLabel
                        active={orderBy === column.field}
                        direction={orderBy === column.field ? order : 'asc'}
                        onClick={createSortHandler(column.field)}
                      >
                        {column.headerName}
                        {orderBy === column.field ? (
                          <span className={classes.visuallyHidden}>
                            {order === 'desc' ? 'sorted descending' : 'sorted ascending'}
                          </span>
                        ) : null}
                      </TableSortLabel>
                    </TableCell>
                  ))}
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                  <TableRow key={row.id}>
                    {columns.map((column) => (
                      <TableCell key={`${row.id}-${column.field}`}>
                        {row[column.field]}
                      </TableCell>
                    ))}
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
          <TablePagination
            rowsPerPageOptions={[5, 8, 10, 20, 50, 100]}
            component="div"
            count={total}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
          />
        </Paper>
      </Box>
    </ThemeProvider>
  );
};

export default InvoiceMuiTable;
