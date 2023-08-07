import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import Link from "@mui/material/Link";

const Footer = () => {
  return (
    <Box
      sx={{
        bgcolor: "primary.main",
        py: 2,
      }}
    >
      <Grid
        container
        sx={{
          color: "#505050",
        }}
        justifyContent="center"
      >
        <Link href="#" color="link.main" underline="always">
          Privacy Policy.
        </Link>
        | © 2023 HighRadius Corporation. All rights reserved.
      </Grid>
    </Box>
  );
};

export default Footer;
