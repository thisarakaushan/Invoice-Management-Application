import React, { useState } from 'react';
import './Table.css';

const Table = () => {
  const [selectedRows, setSelectedRows] = useState([]);
  const data = [
    {
      slNo: 1,
      customerOrderId: '754349803',
      salesOrg: '3911',
      distributionChannel: 'United Arab Emirates',
      companyCode: '3290',
      orderCreationDate: '01-01-2022',
      orderAmount: '1405.54',
      orderCurrency: 'AED',
      customerNumber: '1210499770',
    },
     {
    slNo: 2,
    customerOrderId: '930253442',
    salesOrg: '2381',
    distributionChannel: 'Greece',
    companyCode: '3290',
    orderCreationDate: '01-01-2022',
    orderAmount: '1441.4835',
    orderCurrency: 'EUR',
    customerNumber: '1210351400',
  },
     {
    slNo: 3,
    customerOrderId: '819741436',
    salesOrg: '3605',
    distributionChannel: 'Argentina',
    companyCode: '3290',
    orderCreationDate: '01-01-2022',
    orderAmount: '1065.33',
    orderCurrency: 'EUR',
    customerNumber: '1210124309',
  },
  {
    slNo: 4,
    customerOrderId: '881355361',
    salesOrg: '3645',
    distributionChannel: 'Armenia',
    companyCode: '3470',
    orderCreationDate: '02-01-2022',
    orderAmount: '302.85',
    orderCurrency: 'EUR',
    customerNumber: '12311152',
  },
  {
    slNo: 5,
    customerOrderId: '821659852',
    salesOrg: '2470',
    distributionChannel: 'United States of America',
    companyCode: '3220',
    orderCreationDate: '02-01-2022',
    orderAmount: '8380.69',
    orderCurrency: 'EUR',
    customerNumber: '1230021722',
  },
  {
    slNo: 6,
    customerOrderId: '957194828',
    salesOrg: '3150',
    distributionChannel: 'United States Minor Outlying Islands',
    companyCode: '3290',
    orderCreationDate: '02-01-2022',
    orderAmount: '545.85',
    orderCurrency: 'EUR',
    customerNumber: '1210183107',
  },
  {
    slNo: 7,
    customerOrderId: '806322513',
    salesOrg: '3396',
    distributionChannel: 'Serbia',
    companyCode: '3290',
    orderCreationDate: '02-01-2022',
    orderAmount: '545.85',
    orderCurrency: 'EUR',
    customerNumber: '1210499770',
  },
  {
    slNo: 8, 
    customerOrderId: '922237131',
    salesOrg: '2353',
    distributionChannel: 'Turks and Caicos Islands',
    companyCode: '3290',
    orderCreationDate: '02-01-2022',
    orderAmount: '562.73',
    orderCurrency: 'EUR',
    customerNumber: '1210111951',
  },
];

const handleHeaderCheckboxChange = () => {
  if (selectedRows.length === data.length) {
    setSelectedRows([]);
  } else {
    setSelectedRows(data.map((item) => item.slNo));
  }
};

const handleRowCheckboxChange = (slNo) => {
  setSelectedRows((prevSelectedRows) => {
    if (prevSelectedRows.includes(slNo)) {
      return prevSelectedRows.filter((row) => row !== slNo);
    } else {
      return [...prevSelectedRows, slNo];
    }
  });
};

  return (
    <div className="table-container">
      <table className="data-table">
        <thead>
          <tr>
            <td>
              <input type="checkbox" className='check-input' checked={selectedRows.length === data.length}
                onChange={handleHeaderCheckboxChange}/>
            </td>
            <th>SI. No</th>
            <th>Customer Order ID</th>
            <th>Sales Org</th>
            <th>Distribution Channel</th>
            <th>Company Code</th>
            <th>Order Creation Date</th>
            <th>Order Amount</th>
            <th>Order Currency</th>
            <th>Customer Number</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item) => (
            <tr key={item.slNo}>
              <td>
                <input type="checkbox" className='check-input' checked={selectedRows.includes(item.slNo)}
                  onChange={() => handleRowCheckboxChange(item.slNo)}/>
              </td>
              <td>{item.slNo}</td>
              <td>{item.customerOrderId}</td>
              <td>{item.salesOrg}</td>
              <td>{item.distributionChannel}</td>
              <td>{item.companyCode}</td>
              <td>{item.orderCreationDate}</td>
              <td>{item.orderAmount}</td>
              <td>{item.orderCurrency}</td>
              <td>{item.customerNumber}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Table;
