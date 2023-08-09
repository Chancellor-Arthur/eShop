import { Global } from "@mantine/core";
import { FC } from "react";
import {
  latoBlackItalicTtf,
  latoBlackTtf,
  latoBoldItalicTtf,
  latoBoldTtf,
  latoHairlineItalicTtf,
  latoHairlineTtf,
  latoHeavyItalicTtf,
  latoHeavyTtf,
  latoItalicTtf,
  latoLightItalicTtf,
  latoLightTtf,
  latoMediumItalicTtf,
  latoMediumTtf,
  latoRegularTtf,
  latoSemiBoldItalicTtf,
  latoSemiBoldTtf,
  latoThinItalicTtf,
  latoThinTtf,
} from "@/shared/fonts";

const Fonts: FC = () => {
  return (
    <Global
      styles={[
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Hairline'), local('Lato-Hairline'), url('${latoHairlineTtf}') format('truetype')`,
            fontWeight: 100,
            fontStyle: "normal",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Hairline Italic'), local('Lato-HairlineItalic'), url('${latoHairlineItalicTtf}') format('truetype')`,
            fontWeight: 100,
            fontStyle: "italic",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Thin'), local('Lato-Thin'), url('${latoThinTtf}') format('truetype')`,
            fontWeight: 200,
            fontStyle: "normal",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Thin Italic'), local('Lato-ThinItalic'), url('${latoThinItalicTtf}') format('truetype')`,
            fontWeight: 200,
            fontStyle: "italic",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Light'), local('Lato-Light'), url('${latoLightTtf}') format('truetype')`,
            fontWeight: 300,
            fontStyle: "normal",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Light Italic'), local('Lato-LightItalic'), url('${latoLightItalicTtf}') format('truetype')`,
            fontWeight: 300,
            fontStyle: "italic",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Regular'), local('Lato-Regular'), url('${latoRegularTtf}') format('truetype')`,
            fontWeight: 400,
            fontStyle: "normal",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Italic'), local('Lato-Italic'), url('${latoItalicTtf}') format('truetype')`,
            fontWeight: 400,
            fontStyle: "italic",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Medium'), local('Lato-Medium'), url('${latoMediumTtf}') format('truetype')`,
            fontWeight: 500,
            fontStyle: "normal",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Medium Italic'), local('Lato-MediumItalic'), url('${latoMediumItalicTtf}') format('truetype')`,
            fontWeight: 500,
            fontStyle: "italic",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Semibold'), local('Lato-Semibold'), url('${latoSemiBoldTtf}') format('truetype')`,
            fontWeight: 600,
            fontStyle: "normal",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Semibold Italic'), local('Lato-SemiboldItalic'), url('${latoSemiBoldItalicTtf}') format('truetype')`,
            fontWeight: 600,
            fontStyle: "italic",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Bold'), local('Lato-Bold'), url('${latoBoldTtf}') format('truetype')`,
            fontWeight: 700,
            fontStyle: "normal",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Bold Italic'), local('Lato-BoldItalic'), url('${latoBoldItalicTtf}') format('truetype')`,
            fontWeight: 700,
            fontStyle: "italic",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Heavy'), local('Lato-Heavy'), url('${latoHeavyTtf}') format('truetype')`,
            fontWeight: 800,
            fontStyle: "normal",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Heavy Italic'), local('Lato-HeavyItalic'), url('${latoHeavyItalicTtf}') format('truetype')`,
            fontWeight: 800,
            fontStyle: "italic",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Black'), local('Lato-Black'),url('${latoBlackTtf}') format('truetype')`,
            fontWeight: 900,
            fontStyle: "normal",
          },
        },
        {
          "@font-face": {
            fontFamily: "Lato",
            src: `local('Lato Black Italic'), local('Lato-BlackItalic'), url('${latoBlackItalicTtf}') format('truetype')`,
            fontWeight: 900,
            fontStyle: "italic",
          },
        },
      ]}
    />
  );
};

export default Fonts;
