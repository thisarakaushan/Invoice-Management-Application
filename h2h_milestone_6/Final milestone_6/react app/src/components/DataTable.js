import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { DataGrid } from '@mui/x-data-grid';
import './DataTable.css'; // Import the CSS file

const DataTable = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await axios.get('http://localhost:8081/first1/data_loading');
      setData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
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

  const rows = data.map((item) => ({
    id: item.slNo,
    ...item,
  }));

  const paginationSettings = {
    pageSize: 8,
    pageSizeOptions: [5, 8, 10, 20, 50, 100],
  };

  const getRowHeight = () => {
    return 40;
  };

  // const getRowClassName = (params) => {
  //   if (params.rowIndex === 0) {
  //     return 'first-row';
  //   } else if (params.rowIndex === rows.length - 1) {
  //     return 'last-row';
  //   }
  //   return '';
  // };

  return (
    <div className='body'>
      <div className="table-container">
        <DataGrid
          rows={rows}
          columns={columns}
          pagination
          checkboxSelection
          disableSelectionOnClick
          getRowHeight={getRowHeight}
          // getRowClassName={getRowClassName}
          {...paginationSettings}
        />
      </div>
    </div>

  );
};

export default DataTable;

// import React, { useEffect, useState } from 'react';
// import axios from 'axios';
// import { Table, TableContainer, TableHead, TableRow, TableCell, TableBody, Paper } from '@mui/material';

// const DataTable = () => {
//   const [data, setData] = useState([]);

//   useEffect(() => {
//     fetchData();
//   }, []);

//   const fetchData = async () => {
//     try {
//       const response = await axios.get('http://localhost:8081/first1/data_loading');
//       setData(response.data);
//     } catch (error) {
//       console.error('Error fetching data:', error);
//     }
//   };

//   const columns = [
//     { field: 'slNo', headerName: 'Sl no', width: 100 },
//     { field: 'customerOrderId', headerName: 'CUSTOMER ORDER ID', width: 150 },
//     { field: 'salesOrg', headerName: 'SALES ORG', width: 120 },
//     { field: 'distributionChannel', headerName: 'DISTRIBUTION CHANNEL', width: 180 },
//     { field: 'companyCode', headerName: 'COMPANY CODE', width: 120 },
//     { field: 'orderCreationDate', headerName: 'ORDER CREATION DATE', width: 180 },
//     { field: 'orderAmount', headerName: 'ORDER AMOUNT', width: 140 },
//     { field: 'orderCurrency', headerName: 'ORDER CURRENCY', width: 140 },
//     { field: 'customerNumber', headerName: 'CUSTOMER NUMBER', width: 140 },
//     { field: 'amountInUSD', headerName: 'AMOUNT IN USD', width: 140 },
//   ];

//   const rows = data.map((item) => ({
//     id: item.slNo,
//     ...item,
//   }));

//   return (
//     <TableContainer component={Paper}>
//       <Table>
//         <TableHead>
//           <TableRow>
//             {columns.map((column) => (
//               <TableCell key={column.field}>{column.headerName}</TableCell>
//             ))}
//           </TableRow>
//         </TableHead>
//         <TableBody>
//           {rows.map((row) => (
//             <TableRow key={row.id}>
//               {columns.map((column) => (
//                 <TableCell key={`${row.id}-${column.field}`}>{row[column.field]}</TableCell>
//               ))}
//             </TableRow>
//           ))}
//         </TableBody>
//       </Table>
//     </TableContainer>
//   );
// };

// export default DataTable;
