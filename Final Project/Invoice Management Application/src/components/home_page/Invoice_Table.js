import React, { useEffect, useState, useRef } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { ThemeProvider, createTheme } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Checkbox from '@material-ui/core/Checkbox';
import Paper from '@material-ui/core/Paper';
import TablePagination from '@material-ui/core/TablePagination';
import TableSortLabel from '@material-ui/core/TableSortLabel';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import axios from 'axios';
import TextField from '@material-ui/core/TextField';
import { CircularProgress } from '@material-ui/core';
import { Button, Typography } from '@material-ui/core';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';

const theme = createTheme({
  palette: {
    type: 'dark',
    background: {
      default: '#505050',
    },
    text: {
      primary: '#ffffff',
    },
    secondary: {
      main: '#FF8C00',
    },
    transparentBlue: {
      main: 'rgba(88, 134, 174, 0.15)',
    },
    tabBackground: {
      main: '#505050',
    },

  },
  overrides: {
    MuiCheckbox: {
      indeterminate: {
        color: '#FF8C00',
      },
    },
    MuiTablePagination: {
      root: {
        backgroundColor: '#505050',
        borderTop: '1px solid rgba(255, 255, 255, 0.1)',
      },
      toolbar: {
        height: '48px',
        minHeight: '48px',
        paddingLeft: '16px',
        paddingRight: '16px',
      },
      select: {
        '&:focus': {
          backgroundColor: 'transparent',
        },
      },
      selectIcon: {
        color: '#ffffff',
      },
      input: {
        color: '#ffffff',
        marginRight: '8px',
      },
      menuItem: {
        '&:hover': {
          backgroundColor: 'rgba(255, 255, 255, 0.1)',
        },
      },
      caption: {
        color: '#ffffff',
      },
      selectedTab: {
        main: '#ffffff'
      }
    },
  },
});

const useStyles = makeStyles({
  table: {
    width: '100%',
  },
  selectedCheckbox: {
    color: theme.palette.secondary.main,
  },
  selectedRow: {
    backgroundColor: theme.palette.transparentBlue.main,
  },
  tableCell: {
    height: '100%',
    padding: '8px',
    whiteSpace: 'nowrap',
    overflowX: 'auto',
  },
  tableWrapper: {
    overflowX: 'visible',
  },
  paginationContainer: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-start',
    '& > *': {
      marginRight: theme.spacing(1),
    },
  },
  paginationRoot: {
    backgroundColor: theme.palette.background.default,
    borderTop: `1px solid ${theme.palette.text.primary}`,
  },
  paginationToolbar: {
    height: '48px',
    minHeight: '48px',
    paddingLeft: '16px',
    paddingRight: '16px',
  },
  paginationSelect: {
    '&:focus': {
      backgroundColor: 'transparent',
    },
  },
  paginationSelectIcon: {
    color: theme.palette.text.primary,
  },
  paginationInput: {
    color: theme.palette.text.primary,
    marginRight: '8px',
  },
  paginationMenuItem: {
    '&:hover': {
      backgroundColor: 'rgba(255, 255, 255, 0.1)',
    },
  },
  paginationCaption: {
    color: theme.palette.text.primary,
  },
  formDataWrapper: {
    backgroundColor: theme.palette.background.default,
    padding: '16px',
    transition: 'background-color 0.3s ease',
  },
  selectedFormDataWrapper: {
    backgroundColor: theme.palette.tabBackground.main,
  },
  buttonText: {
    whiteSpace: 'normal',
    lineHeight: '0.7',
    fontSize: '12px',
    color: '#606060',
  },
});

const MyTable = () => {
  const classes = useStyles();
  const [tableData, setTableData] = useState([]);
  const [selectedRows, setSelectedRows] = useState([]);
  const [selectAll, setSelectAll] = useState(false);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(8);
  const [sortBy, setSortBy] = useState('');
  const [sortOrder, setSortOrder] = useState('asc');
  const [activeTab, setActiveTab] = useState('home');
  const headerCheckboxRef = useRef(null);
  const [formData, setFormData] = useState({});
  const [searchValue, setSearchValue] = useState('');
  const [showSearchResultTab, setShowSearchResultTab] = useState(false);
  const [searchResult, setSearchResult] = useState([]);
  const [isSearchResultAvailable, setIsSearchResultAvailable] = useState(true);
  const [open, setOpen] = useState(false);
  const [isPopupOpen, setIsPopupOpen] = useState(false); 
  const [selectedRowData, setSelectedRowData] = useState({}); 
  const [isDeletePopupOpen, setIsDeletePopupOpen] = useState(false);

  const [searchFields, setSearchFields] = useState({
    customerOrderId: '',
    customerNumber: '',
    salesOrg: ''
  });

  const [editField, seteditField] = useState({ 
    orderCurrency: '',
    companyCode: '',
    distributionChannel: ''
  }); 

  const rowsPerPageOptions = [5, 8, 10, 20, 50, 100];

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
      }}
    setSelectedRows(selectedRowIds);
  };

  const handleSelectAllChange = (event) => {
    const isChecked = event.target.checked;
    const selectedRowIds = isChecked ? tableData.map((row) => row.customerOrderId) : [];
    setSelectedRows(selectedRowIds);
    setSelectAll(isChecked);
  };

  useEffect(() => {
    const areAllRowsSelected = tableData.length > 0 && selectedRows.length === tableData.length;
    setSelectAll(areAllRowsSelected);
  }, [tableData, selectedRows]);

  useEffect(() => {
    const indeterminate = selectedRows.length > 0 && selectedRows.length < tableData.length;
    if (headerCheckboxRef.current) {
      headerCheckboxRef.current.indeterminate = indeterminate;

    }
  }, [selectedRows, tableData]);

  const handleSortChange = (field) => {
    if (field === sortBy) {
      setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
    } else {
      setSortBy(field);
      setSortOrder('asc');
    }
  };

  const sortedData = [...tableData].sort((a, b) => {
    const aValue = a[sortBy];
    const bValue = b[sortBy];
    if (aValue < bValue) return sortOrder === 'asc' ? -1 : 1;
    if (aValue > bValue) return sortOrder === 'asc' ? 1 : -1;
    return 0;
  });

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

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const paginatedData = sortedData.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage);

  const handleTabChange = (event, newTab) => {
    setActiveTab(newTab);
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));

    // Perform the search request
    fetch(`http://localhost:8081/first1/data_loading?customerOrderId=${value}`)
      .then((response) => response.json())
      .then((data) => {
        if (data.length > 0) {
          setSearchResult(data);
          setIsSearchResultAvailable(true);
        } else {
          setSearchResult([]);
          setIsSearchResultAvailable(false);
        }
      })
      .catch((error) => {
        console.error('Error searching for customer order ID:', error);
      });
  };

  const handleDateChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  // Edit button
  const handleEditButtonClick = () => {
    // Retrieve the selected row's data
    const selectedRow = tableData.find(row => row.customerOrderId === selectedRows[0]);
    setSelectedRowData(selectedRow);
    setIsPopupOpen(true);
  };

  const handleClosePopup = () => {
    setIsPopupOpen(false);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
  };

  // Delete button
  const handleDeleteButtonClick = () => {
    setIsDeletePopupOpen(true);
  };

  const handleCloseDeletePopup = () => {
    setIsDeletePopupOpen(false);
  };

  const handleConfirmDelete = () => {
    setIsDeletePopupOpen(false);
  };

  const handleAdvancedInputChange = (event) => {
    setSearchFields((prevFields) => ({
      ...prevFields,
      [event.target.name]: event.target.value
    }));
  };

  // Function to handle the form submission
  const handleSearch = (event) => {
    event.preventDefault();
    console.log(searchFields);
    handleClose();
  };

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleAddData = () => {
    console.log('Form Data:', formData);
    // Make a POST request to the backend API
    axios.post('http://localhost:8081/first1/add_servlet', formData)
      .then(response => {
        console.log('Add Data Response:', response.data);
        setFormData({});
      })
      .catch(error => {
        console.error('Add Data Error:', error);
      });
  };

  const handleClearData = () => {
    setFormData({});
  };

  const handleKeyPress = (event) => {
    if (event.key === 'Enter') {
      setShowSearchResultTab(true);

      if (setShowSearchResultTab) {
        setActiveTab('searchResult');
      }
    }
  };

  const handleSearchChange = (event) => {
    setSearchValue(event.target.value);
  };

  return (
    <ThemeProvider theme={theme}>
      <Tabs
        value={activeTab}
        onChange={handleTabChange}
        indicatorColor="secondary"
        textColor="primary"
        style={{ backgroundColor: theme.palette.tabBackground.main }}
      >
        <Tab
          label="HOME PAGE"
          value="home"
          style={{
            color: '#ffffff',
          }}
        />
        <Tab
          label="ADD DATA"
          value="addData"
          style={{ color: '#ffffff' }}
        />
        {showSearchResultTab && (
          <Tab
            label="SEARCH RESULT"
            value="searchResult"
            style={{ color: '#ffffff' }}
          />
        )}
        <Tab
          label="ANALYTICS VIEW"
          value="analyticsView"
          style={{ color: '#ffffff' }}
          />
        <div style={{ marginLeft: 'auto', display: 'flex', alignItems: 'center', justifyContent: 'flex-end' }}>
          <input type="text" placeholder="Search Customer Order ID" style={{ padding: '8px', height: '25px' }} value={searchValue}
            onChange={handleSearchChange}
            onKeyDown={handleKeyPress} />
          {activeTab === 'searchResult' && (
            <Button
              variant="outlined"
              style={{ backgroundColor: '#e64343', padding: '10px', height: '45px', width: '100px', marginRight: '15px', marginTop: '5px', 
              marginBottom: '5px', marginLeft: '15px' }}>
              <Typography variant="body1" className={classes.buttonText}>
                Clear
              </Typography>
            </Button>
          )}
          {activeTab !== 'searchResult' && (
            <Button
              variant="outlined"
              style={{ backgroundColor: 'lightgreen', padding: '10px', width: '100px', marginRight: '15px', 
              marginTop: '5px', marginBottom: '5px', marginLeft: '15px' }}
              onClick={handleOpen}>
              <Typography variant="body1" className={classes.buttonText}>
                ADVANCED<br /><br />SEARCH
              </Typography>
            </Button>
          )}
          <Dialog open={open} onClose={handleClose} style={{ width: '375px', left: '72%', top: '15%' }}>
            <DialogTitle style={{ backgroundColor: '#505050', color: 'white' }}>
              Advanced Search
              </DialogTitle>
            <DialogContent style={{ backgroundColor: '#505050' }}>
              <form onSubmit={handleSearch}>
                <TextField
                  name="customerOrderId"
                  label="Customer Order Id"
                  value={searchFields.customerOrderId}
                  onChange={handleAdvancedInputChange}
                  fullWidth
                  margin="normal"
                  InputLabelProps={{
                    style: { color: 'white' }
                  }}
                  InputProps={{
                    style: { color: 'white' }
                  }}/>
                <TextField
                  name="customerNumber"
                  label="Customer Number"
                  value={searchFields.customerNumber}
                  onChange={handleAdvancedInputChange}
                  fullWidth
                  margin="normal"
                  InputLabelProps={{
                    style: { color: 'white' }
                  }}
                  InputProps={{
                    style: { color: 'white' }
                  }}/>
                <TextField
                  name="salesOrg"
                  label="Sales Org"
                  value={searchFields.salesOrg}
                  onChange={handleAdvancedInputChange}
                  fullWidth
                  margin="normal"
                  InputLabelProps={{
                    style: { color: 'white' }
                  }}
                  InputProps={{
                    style: { color: 'white' }
                  }}/>
              </form>
            </DialogContent>
            <DialogActions style={{ backgroundColor: '#505050' }}>
              <Button onClick={handleSearch} color="primary" type="submit" 
              style={{ backgroundColor: "lightgreen", color: "black" }} fullWidth>
                Search
              </Button>
              <Button onClick={handleClose} style={{ backgroundColor: "#e64343" }} fullWidth>
                Cancel
              </Button>
            </DialogActions>
          </Dialog>
        </div>
      </Tabs>
      {activeTab === 'home' && (
        <div className={classes.tableWrapper}>
          <TableContainer component={Paper} style={{ borderRadius: '0px' }}>
            <Table className={classes.table} aria-label="My Table" style={{ background: theme.palette.tabBackground.main, }}>
              <TableHead>
                <TableRow>
                  <TableCell className={classes.tableCell}>
                    <Checkbox
                      id="header-checkbox"
                      checked={selectAll}
                      ref={headerCheckboxRef}
                      onChange={handleSelectAllChange}
                      indeterminate={selectedRows.length > 0 && selectedRows.length < tableData.length}
                      classes={{ checked: classes.selectedCheckbox }}/>
                  </TableCell>
                  {columns.map((column) => (
                    <TableCell key={column.field} className={classes.tableCell}>
                      <TableSortLabel
                        active={sortBy === column.field}
                        direction={sortBy === column.field ? sortOrder : 'asc'}
                        onClick={() => handleSortChange(column.field)}>
                        {column.label}
                      </TableSortLabel>
                    </TableCell>
                  ))}
                </TableRow>
              </TableHead>
              <TableBody>
                {paginatedData.map((row, index) => (
                  <TableRow
                    key={index}
                    className={selectedRows.includes(row.customerOrderId) ? classes.selectedRow : ''}
                    style={{
                      backgroundColor: selectedRows.includes(row.customerOrderId)
                        ? theme.palette.transparentBlue.main
                        : 'transparent',
                    }}>
                    <TableCell className={classes.tableCell}>
                      <Checkbox
                        ref={headerCheckboxRef}
                        checked={selectedRows.includes(row.customerOrderId)}
                        onChange={(event) => handleCheckboxChange(event, row)}
                        color="secondary"/>
                    </TableCell>
                    {columns.map((column) => (
                      <TableCell key={column.field} className={classes.tableCell}>
                        {row[column.field]}
                      </TableCell>
                    ))}
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </div>
      )}
      {activeTab === 'addData' && (
        <div className={`${classes.formDataWrapper} ${classes.selectedFormDataWrapper}`}>
          {/* Add Data form */}
          <form method='get'>
            <TableContainer>
              <Table>
                <TableBody>
                  <TableRow style={{ width: '100%' }}>
                    <TableCell style={{ width: '20%' }}>
                      <TextField
                        name="customerOrderId"
                        label="Customer Order ID"
                        value={formData.customerOrderId || ''}
                        onChange={handleInputChange}
                      />
                    </TableCell>
                    <TableCell style={{ width: '20%' }}>
                      <TextField
                        name="salesOrg"
                        label="Sales Org"
                        value={formData.salesOrg || ''}
                        onChange={handleInputChange}
                      />
                    </TableCell>
                    <TableCell colSpan={3} >
                      <TextField
                        name="distributionChannel"
                        label="Distribution Channel"
                        value={formData.distributionChannel || ''}
                        onChange={handleInputChange}
                        fullWidth
                      />
                    </TableCell>
                  </TableRow>
                  <TableRow>
                    <TableCell style={{ width: '20%' }}>
                      <TextField
                        name="customerNumber"
                        label="Customer Number"
                        value={formData.customerNumber || ''}
                        onChange={handleInputChange}
                      />
                    </TableCell>
                    <TableCell style={{ width: '20%' }}>
                      <TextField
                        name="companyCode"
                        label="Company Code"
                        value={formData.companyCode || ''}
                        onChange={handleInputChange}
                      />
                    </TableCell>
                    <TableCell>
                      <TextField
                        name="orderCurrency"
                        label="Order Currency"
                        value={formData.orderCurrency || ''}
                        onChange={handleInputChange}
                      />
                    </TableCell>
                    <TableCell>
                      <TextField
                        name="amountInUSD"
                        label="Amount in USD"
                        value={formData.amountInUSD || ''}
                        onChange={handleInputChange}
                      />
                    </TableCell>
                    <TableCell style={{ width: '20%' }}>
                      <TextField
                        name="orderCreationDate"
                        label="Order Creation Date"
                        type="date"
                        value={formData.orderCreationDate ? formData.orderCreationDate : ''}
                        onChange={handleDateChange}
                        InputLabelProps={{
                          shrink: true,
                        }}
                      />
                    </TableCell>
                  </TableRow>
                  <TableRow >
                    <TableCell colSpan={2}>
                      <Button variant="contained" color="primary" onClick={handleAddData} fullWidth>
                        ADD
                      </Button>
                    </TableCell>
                    <TableCell colSpan={3}>
                      <Button variant="contained" color="secondary" onClick={handleClearData} fullWidth>
                        CLEAR DATA
                      </Button>
                    </TableCell>
                  </TableRow>
                </TableBody>
              </Table>
            </TableContainer>
          </form>
        </div>
      )}
      {activeTab === 'searchResult' && (
        <div>
          {isSearchResultAvailable ? (
            <div>
              {searchResult.length > 0 ? (
                <div>
                  {searchResult.map((result) => (
                    <div key={result.id}>{result.name}</div>
                  ))}
                </div>
              ) : (
                <div>No rows available for display.</div>
              )}
            </div>
          ) : (
            <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100px' }}>
              <CircularProgress />
            </div>
          )}
        </div>
      )}
      <div style={{ display: "flex", flexDirection: "row" }}>
        <div
          style={{ width: "100%", padding: "10px", background: "#505050", display: "flex", flexDirection: "row" }}>
          <Button style={{ background: "#FF8C00", marginRight: "5px", height: "30px", fontSize: "0.7em" }}>
            REFRESH DATA
          </Button>
          {selectedRows.length == 1 && (
            <div style={{ display: "flex", flexDirection: "row" }} >
              <Button style={{ background: "#FF8C00", marginRight: "5px", height: "30px", fontSize: "0.7em" }}
                onClick={handleEditButtonClick}>
                EDIT
              </Button>
              {/* Pop-up window FOR EDIT*/}
              <Dialog open={isPopupOpen} onClose={handleClosePopup} style={{ width: '100%', top: '28%', left: '-23%' }}>
                <DialogTitle style={{ backgroundColor: '#505050', color: 'white', paddingBottom: '0px', paddingRight: '0px' }}>
                  Edit
                </DialogTitle>
                <DialogContent style={{ backgroundColor: '#505050', paddingLeft: '0px', paddingRight: '0px', paddingBottom: '0px' }}>
                  <form onSubmit={handleSubmit}>
                    <Table >
                      <TableRow>
                        <TableCell style={{ paddingTop: '0px' }}>
                          <TextField
                            name="orderCurrency"
                            label="ORDER CURRENCY"
                            value={formData.orderCurrency}
                            onChange={handleInputChange}
                            fullWidth
                            margin="normal"
                            InputLabelProps={{
                              style: { color: 'white', fontSize: '0.8em' }
                            }}
                            InputProps={{
                              style: { color: 'white' }
                            }}
                          />
                        </TableCell>
                        <TableCell style={{ paddingTop: '0px' }}>
                          <TextField
                            name="companyCode"
                            label="COMPANY CODE"
                            value={formData.companyCode}
                            onChange={handleInputChange}
                            fullWidth
                            margin="normal"
                            InputLabelProps={{
                              style: { color: 'white', fontSize: '0.8em' }
                            }}
                            InputProps={{
                              style: { color: 'white' }
                            }}
                          />
                        </TableCell>
                      </TableRow>
                      <TableRow>
                        <TableCell colSpan={2} style={{ paddingTop: '0px' }}>
                          <TextField
                            name="distributionChannel"
                            label="DISTRIBUTION CHANNEL"
                            value={formData.distributionChannel}
                            onChange={handleInputChange}
                            fullWidth
                            margin="normal"
                            InputLabelProps={{
                              style: { color: 'white', fontSize: '0.8em' }
                            }}
                            InputProps={{
                              style: { color: 'white' }
                            }}
                          />
                        </TableCell>
                      </TableRow>
                    </Table>
                  </form>
                </DialogContent>
                <DialogActions style={{ backgroundColor: '#505050' }}>
                  <Button
                    onClick={handleClosePopup}
                    color="primary"
                    type="submit"
                    style={{ backgroundColor: "lightgreen", color: "black" }}
                    fullWidth>
                    EDIT
                  </Button>
                  <Button onClick={handleClosePopup} style={{ backgroundColor: "#e64343" }} fullWidth>
                    CANCEL
                  </Button>
                </DialogActions>
              </Dialog>
              <Button style={{ background: "#FF8C00", marginRight: "5px", height: "30px", fontSize: "0.7em" }}
                onClick={handleDeleteButtonClick}>
                DELETE</Button>
              {/* Delete Confirmation Pop-up window */}
              <Dialog open={isDeletePopupOpen} onClose={handleCloseDeletePopup}>
                <DialogTitle>Delete Confirmation</DialogTitle>
                <DialogContent>
                  Are you sure you want to delete the selected row(s)?
                </DialogContent>
                <DialogActions>
                  <Button onClick={handleCloseDeletePopup} color="primary">
                    Cancel
                  </Button>
                  <Button onClick={handleConfirmDelete} color="secondary">
                    Delete
                  </Button>
                </DialogActions>
              </Dialog>
              <Button style={{ background: "#FF8C00", marginRight: "5px", height: "30px", fontSize: "0.7em" }}>
                PREDICT
              </Button>
            </div>
          )}
          {selectedRows.length > 1 && (
            <div style={{ display: "flex", flexDirection: "row" }} >
              <Button style={{ background: "#404040", marginRight: "5px", height: "30px", fontSize: "0.7em" }}
                disabled={true}>EDIT</Button>
              <Button style={{ background: "#FF8C00", marginRight: "5px", height: "30px", fontSize: "0.7em" }}
                onClick={handleDeleteButtonClick}>
                DELETE
              </Button>
              {/* Delete Confirmation Pop-up window */}
              <Dialog open={isDeletePopupOpen} onClose={handleCloseDeletePopup}>
                <DialogTitle>Delete Records?</DialogTitle>
                <DialogContent>
                  Are you sure you want to delete the selected row(s)?
                </DialogContent>
                <DialogActions>
                  <Table>
                    <TableRow style={{ width: "100%" }}>
                      <TableCell style={{ width: "100%" }}>
                        <Button onClick={handleCloseDeletePopup} color="primary">
                          CANCEL
                        </Button>
                      </TableCell>
                      <TableCell style={{ width: "100%" }}>
                        <Button onClick={handleConfirmDelete} color="secondary">
                          DELETE
                        </Button>
                      </TableCell>
                    </TableRow>
                  </Table>
                </DialogActions>
              </Dialog>
              <Button style={{ background: "#FF8C00", marginRight: "5px", height: "30px", fontSize: "0.7em" }}>
                PREDICT
              </Button>
            </div>
          )}
          {
            selectedRows.length == 0 && (
              <div>
                <Button style={{ background: "#404040", marginRight: "5px", height: "30px", fontSize: "0.7em" }}
                  disabled={true}>EDIT</Button>
                <Button style={{ background: "#404040", marginRight: "5px", height: "30px", fontSize: "0.7em" }}
                  disabled={true}>DELETE</Button>
                <Button style={{ background: "#404040", marginRight: "5px", height: "30px", fontSize: "0.7em" }}
                  disabled={true}>PREDICT</Button>
              </div>
            )
          }
        </div>
        <TablePagination
          style={{ width: "100%", marginTop: "0px" }}
          classes={{
            root: classes.paginationRoot,
            toolbar: classes.paginationToolbar,
            select: classes.paginationSelect,
            selectIcon: classes.paginationSelectIcon,
            input: classes.paginationInput,
            menuItem: classes.paginationMenuItem,
            caption: classes.paginationCaption,
          }}
          component="div"
          count={tableData.length}
          rowsPerPage={rowsPerPage}
          page={page}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleChangeRowsPerPage}
          rowsPerPageOptions={rowsPerPageOptions}
          labelRowsPerPage="Rows per page:"
        />
      </div>
    </ThemeProvider>
  );
};

export default MyTable;