import React from "react";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import HRCLogo from "../resources/hrc_logo.jpg";
import ABCProductsLogo from "../resources/ABC-Products-white-logo.svg";
const Header = () => {
  return (
    <Box
      sx={{
        height: "100%",
        bgcolor: "primary.main",
        pt: 2,
      }}
    >
      <Grid
        container
        direction="row"
        justifyContent="space-around"
        alignItems="left"
      >
        <Grid item width={"45%"} height={"100%"} >
          <img src={ABCProductsLogo} alt="ABC Products logo" loading="lazy" alignItems="left" height={40} />
        </Grid>
        <Grid item width={"55%"} height={"100%"}>
          <img src={HRCLogo} alt="highradius logo" loading="lazy" height={35} />
        </Grid>

        
      </Grid>
      <Grid
        container
        direction="row"
        justifyContent="space-around"
        alignItems="left"
        
      >
        <Grid item 
          height={"100%"}
          alignItems={"left"}
          width={"100%"}
          fontSize={"25px" }
          fontWeight={"600"}
          color={"red"}
          padding={"5px"}
        >Invoice List</Grid>
      </Grid>
    </Box>
  );
};
export default Header;
