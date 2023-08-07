import React from 'react';
import Header from "./components/Header";
import Footer from "./components/Footer";
import { createTheme, ThemeProvider } from "@mui/material";
import Invoice_Table from "./components/home_page/Invoice_Table"

const theme = createTheme({
  palette: {
    primary: {
      main: "#fff",
    },
    secondary: {
      main: "#283d4a",
    },
    outline: {
      main: "#fff",
    },
    text: {
      main: "#fff",
    },
    link: {
      main: "#0d6efd",
    },
    focusBlue: {
      main: "#14aff1",
    },
    searchInputText: {
      main: "#616161",
    },
  },
});

const App = () => {
  return (
    <div className="App">
      <ThemeProvider theme={theme}>
        <Header />
        <Invoice_Table />
        <Footer />
      </ThemeProvider>
    </div>
  );
};

export default App;



