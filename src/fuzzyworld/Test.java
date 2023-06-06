/*
 * The MIT License
 *
 * Copyright 2023 Shinacho.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fuzzyworld;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kinugasa.game.GameManager;
import kinugasa.game.GameOption;
import kinugasa.game.GameTimeManager;
import kinugasa.game.GraphicsContext;
import kinugasa.game.input.InputState;
import kinugasa.util.Random;
import kinugasa.util.StringUtil;

/**
 *
 * @vesion 1.0.0 - May 26, 2023_10:03:31 AM<br>
 * @author Shinacho<br>
 */
public class Test extends GameManager {

	static List<String> l = List.of(
			"PG0001",
			"PG0002",
			"PG0003",
			"PG0004",
			"PG0005",
			"PG0006",
			"PG0007",
			"PG0008",
			"PG0009",
			"PG0010",
			"PG0011",
			"PG0012",
			"PG0013",
			"PG0014",
			"PG0015",
			"PG0016",
			"PG0017",
			"PG0018",
			"PG0019",
			"PG0020",
			"PG0021",
			"PG0022",
			"PG0023",
			"PG0024",
			"PG0025",
			"PG0026",
			"PG0028",
			"PG0029",
			"PG0030",
			"PG0031",
			"PG0032",
			"PG0033",
			"PG0034",
			"PG0035",
			"PG0036",
			"PG0037",
			"PG0038",
			"PG0039",
			"PG0040",
			"PG0041",
			"PG0042",
			"PG0043",
			"PG0044",
			"PG0045",
			"PG0046",
			"PG0047",
			"PG0048",
			"PG0049",
			"PG0050",
			"PG0051",
			"PG0052",
			"PG0053",
			"PG0054",
			"PG0055",
			"PG0056",
			"PG0057",
			"PG0058",
			"PG0059",
			"PG0060",
			"PG0061",
			"PG0062",
			"PG0063",
			"PG0064",
			"PG0065",
			"PG0066",
			"PG0067",
			"PG0068",
			"PG0069",
			"PG0070",
			"PG0071",
			"PG0072",
			"PG0073",
			"PG0074",
			"PG0075",
			"PG0076",
			"PG0077",
			"PG0078",
			"PG0079",
			"PG0080",
			"PG0081",
			"PG0082",
			"PG0083",
			"PG0084",
			"PG0085",
			"PG0086",
			"PG0087",
			"PG0088",
			"PG0089",
			"PG0090",
			"PG0091",
			"PG0092",
			"PG0093",
			"PG0094",
			"PG0095",
			"PG0096",
			"PG0097",
			"PG0098",
			"PG0099",
			"PG0100",
			"PG0101",
			"PG0102",
			"PG0103",
			"PG0104",
			"PG0105",
			"PG0106",
			"PG0107",
			"PG0108",
			"PG0109",
			"PG0110",
			"PG0111",
			"PG0112",
			"PG0113",
			"PG0114",
			"PG0115",
			"PG0116",
			"PG0117",
			"PG0118",
			"PG0119",
			"PG0120",
			"PG0121",
			"PG0122",
			"PG0124",
			"PG0125",
			"PG0126",
			"PG0127",
			"PG0128",
			"PG0129",
			"PG0130",
			"PG0131",
			"PG0132",
			"PG0133",
			"PG0134",
			"PG0135",
			"PG0136",
			"PG0137",
			"PG0138",
			"PG0139",
			"PG0140",
			"PG0141",
			"PG0142",
			"PG0143",
			"PG0144",
			"PG0145",
			"PG0146",
			"PG0147",
			"PG0148",
			"PG0149",
			"PG0150",
			"PG0151",
			"PG0152",
			"PG0153",
			"PG0154",
			"PG0155",
			"PG0156",
			"PG0157",
			"PG0158",
			"PG0159",
			"PG0160",
			"PG0161",
			"PG0162",
			"PG0163",
			"PG0164",
			"PG0165",
			"PG0166",
			"PG0167",
			"PG0168",
			"PG0169",
			"PG0170",
			"PG0171",
			"PG0172",
			"PG0173",
			"PG0174",
			"PG0175",
			"PG0176",
			"PG0177",
			"PG0178",
			"PG0179",
			"PG0180",
			"PG0181",
			"PG0182",
			"PG0183",
			"PG0184",
			"PG0185",
			"PG0186",
			"PG0187",
			"PG0188",
			"PG0189",
			"PG0190",
			"PG0191",
			"PG0192",
			"PG0193",
			"PG0194",
			"PG0195",
			"PG0196",
			"PG0197",
			"PG0198",
			"PG0199",
			"PG0200",
			"PG0201",
			"PG0202",
			"PG0203",
			"PG0204",
			"PG0205",
			"PG0206",
			"PG0207",
			"PG0208",
			"PG0209",
			"PG0210",
			"PG0211",
			"PG0212",
			"PG0213",
			"PG0214",
			"PG0215",
			"PG0216",
			"PG0217",
			"PG0218",
			"PG0219",
			"PG0221",
			"PG0222",
			"PG0223",
			"PG0224",
			"PG0225",
			"PG0226",
			"PG0227",
			"PG0228",
			"PG0229",
			"PG0230",
			"PG0231",
			"PG0232",
			"PG0233",
			"PG0234",
			"PG0235",
			"PG0236",
			"PG0237",
			"PG0238",
			"PG0239",
			"PG0240",
			"PG0241",
			"PG0242",
			"PG0243",
			"PG0244",
			"PG0245",
			"PG0246",
			"PG0247",
			"PG0248",
			"PG0249",
			"PG0250",
			"PG0251",
			"PG0252",
			"PG0253",
			"PG0254",
			"PG0255",
			"PG0256",
			"PG0257",
			"PG0258",
			"PG0259",
			"PG0260",
			"PG0261",
			"PG0262",
			"PG0263",
			"PG0264",
			"PG0265",
			"PG0266",
			"PG0267",
			"PG0268",
			"PG0269",
			"PG0270",
			"PG0271",
			"PG0272",
			"PG0273",
			"PG0274",
			"PG0275",
			"PG0276",
			"PG0277",
			"PG0278",
			"PG0279",
			"PG0280",
			"PG0281",
			"PG0282",
			"PG0283",
			"PG0284",
			"PG0285",
			"PG0286",
			"PG0287",
			"PG0288",
			"PG0289",
			"PG0290",
			"PG0291",
			"PG0292",
			"PG0293",
			"PG0294",
			"PG0295",
			"PG0296",
			"PG0297",
			"PG0298",
			"PG0299",
			"PG0300",
			"PG0301",
			"PG0302",
			"PG0303",
			"PG0304",
			"PG0305",
			"PG0306",
			"PG0307",
			"PG0308",
			"PG0309",
			"PG0310",
			"PG0311",
			"PG0312",
			"PG0313",
			"PG0314",
			"PG0315",
			"PG0316",
			"PG0318",
			"PG0319",
			"PG0320",
			"PG0321",
			"PG0322",
			"PG0323",
			"PG0324",
			"PG0325",
			"PG0326",
			"PG0327",
			"PG0328",
			"PG0329",
			"PG0330",
			"PG0331",
			"PG0332",
			"PG0333",
			"PG0334",
			"PG0335",
			"PG0336",
			"PG0337",
			"PG0338",
			"PG0339",
			"PG0340",
			"PG0341",
			"PG0342",
			"PG0343",
			"PG0344",
			"PG0345",
			"PG0346",
			"PG0347",
			"PG0348",
			"PG0349",
			"PG0350",
			"PG0351",
			"PG0352",
			"PG0353",
			"PG0354",
			"PG0355",
			"PG0356",
			"PG0357",
			"PG0358",
			"PG0359",
			"PG0360",
			"PG0361",
			"PG0362",
			"PG0363",
			"PG0364",
			"PG0365",
			"PG0366",
			"PG0367",
			"PG0368",
			"PG0369",
			"PG0370",
			"PG0371",
			"PG0372",
			"PG0373",
			"PG0374",
			"PG0375",
			"PG0376",
			"PG0377",
			"PG0378",
			"PG0379",
			"PG0380",
			"PG0381",
			"PG0382",
			"PG0383",
			"PG0384",
			"PG0385",
			"PG0386",
			"PG0387",
			"PG0388",
			"PG0389",
			"PG0390",
			"PG0391",
			"PG0392",
			"PG0393",
			"PG0394",
			"PG0395",
			"PG0396",
			"PG0397",
			"PG0398",
			"PG0399",
			"PG0400",
			"PG0401",
			"PG0402",
			"PG0403",
			"PG0404",
			"PG0405",
			"PG0406",
			"PG0407",
			"PG0408",
			"PG0409",
			"PG0410",
			"PG0411",
			"PG0412",
			"PG0413",
			"PG0415",
			"PG0416",
			"PG0417",
			"PG0418",
			"PG0419",
			"PG0420",
			"PG0421",
			"PG0422",
			"PG0423",
			"PG0424",
			"PG0425",
			"PG0426",
			"PG0427",
			"PG0428",
			"PG0429",
			"PG0430",
			"PG0431",
			"PG0432",
			"PG0433",
			"PG0434",
			"PG0435",
			"PG0436",
			"PG0437",
			"PG0438",
			"PG0439",
			"PG0440",
			"PG0441",
			"PG0442",
			"PG0443",
			"PG0444",
			"PG0445",
			"PG0446",
			"PG0447",
			"PG0448",
			"PG0449",
			"PG0450",
			"PG0451",
			"PG0452",
			"PG0453",
			"PG0454",
			"PG0455",
			"PG0456",
			"PG0457",
			"PG0458",
			"PG0459",
			"PG0460",
			"PG0461",
			"PG0462",
			"PG0463",
			"PG0464",
			"PG0465",
			"PG0466",
			"PG0467",
			"PG0468",
			"PG0469",
			"PG0470",
			"PG0471",
			"PG0472",
			"PG0473",
			"PG0474",
			"PG0475",
			"PG0476",
			"PG0477",
			"PG0478",
			"PG0479",
			"PG0480",
			"PG0481",
			"PG0482",
			"PG0483",
			"PG0484",
			"PG0485",
			"PG0486",
			"PG0487",
			"PG0488",
			"PG0489",
			"PG0490",
			"PG0491",
			"PG0492",
			"PG0493",
			"PG0494",
			"PG0495",
			"PG0496",
			"PG0497",
			"PG0498",
			"PG0499",
			"PG0500",
			"PG0501",
			"PG0502",
			"PG0503",
			"PG0504",
			"PG0505",
			"PG0506",
			"PG0507",
			"PG0508",
			"PG0509",
			"PG0510",
			"PG0512",
			"PG0513",
			"PG0514",
			"PG0515",
			"PG0516",
			"PG0517",
			"PG0518",
			"PG0519",
			"PG0520",
			"PG0521",
			"PG0522",
			"PG0523",
			"PG0524",
			"PG0525",
			"PG0526",
			"PG0527",
			"PG0528",
			"PG0529",
			"PG0530",
			"PG0531",
			"PG0532",
			"PG0533",
			"PG0534",
			"PG0535",
			"PG0536",
			"PG0537",
			"PG0538",
			"PG0539",
			"PG0540",
			"PG0541",
			"PG0542",
			"PG0543",
			"PG0544",
			"PG0545",
			"PG0546",
			"PG0547",
			"PG0548",
			"PG0549",
			"PG0550",
			"PG0551",
			"PG0552",
			"PG0553",
			"PG0554",
			"PG0555",
			"PG0556",
			"PG0557",
			"PG0558",
			"PG0559",
			"PG0560",
			"PG0561",
			"PG0562",
			"PG0563",
			"PG0564",
			"PG0565",
			"PG0566",
			"PG0567",
			"PG0568",
			"PG0569",
			"PG0570",
			"PG0571",
			"PG0572",
			"PG0573",
			"PG0574",
			"PG0575",
			"PG0576",
			"PG0577",
			"PG0578",
			"PG0579",
			"PG0580",
			"PG0581",
			"PG0582",
			"PG0583",
			"PG0584",
			"PG0585",
			"PG0586",
			"PG0587",
			"PG0588",
			"PG0589",
			"PG0590",
			"PG0591",
			"PG0592",
			"PG0593",
			"PG0594",
			"PG0595",
			"PG0596",
			"PG0597",
			"PG0598",
			"PG0599",
			"PG0600",
			"PG0601",
			"PG0602",
			"PG0603",
			"PG0604",
			"PG0605",
			"PG0606",
			"PG0607",
			"PG0609",
			"PG0610",
			"PG0611",
			"PG0612",
			"PG0613",
			"PG0614",
			"PG0615",
			"PG0616",
			"PG0617",
			"PG0618",
			"PG0619",
			"PG0620",
			"PG0621",
			"PG0622",
			"PG0623",
			"PG0624",
			"PG0625",
			"PG0626",
			"PG0627",
			"PG0628",
			"PG0629",
			"PG0630",
			"PG0631",
			"PG0632",
			"PG0633",
			"PG0634",
			"PG0635",
			"PG0636",
			"PG0637",
			"PG0638",
			"PG0639",
			"PG0640",
			"PG0641",
			"PG0642",
			"PG0643",
			"PG0644",
			"PG0645",
			"PG0646",
			"PG0647",
			"PG0648",
			"PG0649",
			"PG0650",
			"PG0651",
			"PG0652",
			"PG0653",
			"PG0654",
			"PG0655",
			"PG0656",
			"PG0657",
			"PG0658",
			"PG0659",
			"PG0660",
			"PG0661",
			"PG0662",
			"PG0663",
			"PG0664",
			"PG0665",
			"PG0666",
			"PG0667",
			"PG0668",
			"PG0669",
			"PG0670",
			"PG0671",
			"PG0672",
			"PG0673",
			"PG0674",
			"PG0675",
			"PG0676",
			"PG0677",
			"PG0678",
			"PG0679",
			"PG0680",
			"PG0681",
			"PG0682",
			"PG0683",
			"PG0684",
			"PG0685",
			"PG0686",
			"PG0687",
			"PG0688",
			"PG0689",
			"PG0690",
			"PG0691",
			"PG0692",
			"PG0693",
			"PG0694",
			"PG0695",
			"PG0696",
			"PG0697",
			"PG0698",
			"PG0699",
			"PG0700",
			"PG0701",
			"PG0702",
			"PG0703",
			"PG0704",
			"PG0706",
			"PG0707",
			"PG0708",
			"PG0709",
			"PG0710",
			"PG0711",
			"PG0712",
			"PG0713",
			"PG0714",
			"PG0715",
			"PG0716",
			"PG0717",
			"PG0718",
			"PG0719",
			"PG0720",
			"PG0721",
			"PG0722",
			"PG0723",
			"PG0724",
			"PG0725",
			"PG0726",
			"PG0727",
			"PG0728",
			"PG0729",
			"PG0730",
			"PG0731",
			"PG0732",
			"PG0733",
			"PG0734",
			"PG0735",
			"PG0736",
			"PG0737",
			"PG0738",
			"PG0739",
			"PG0740",
			"PG0741",
			"PG0742",
			"PG0743",
			"PG0744",
			"PG0745",
			"PG0746",
			"PG0747",
			"PG0748",
			"PG0749",
			"PG0750",
			"PG0751",
			"PG0752",
			"PG0753",
			"PG0754",
			"PG0755",
			"PG0756",
			"PG0757",
			"PG0758",
			"PG0759",
			"PG0760",
			"PG0761",
			"PG0762",
			"PG0763",
			"PG0764",
			"PG0765",
			"PG0766",
			"PG0767",
			"PG0768",
			"PG0769",
			"PG0770",
			"PG0771",
			"PG0772",
			"PG0773",
			"PG0774",
			"PG0775",
			"PG0776",
			"PG0777",
			"PG0778",
			"PG0779",
			"PG0780",
			"PG0782",
			"PG0783",
			"PG0784",
			"PG0785",
			"PG0786",
			"PG0787",
			"PG0788",
			"PG0789",
			"PG0790",
			"PG0791",
			"PG0792",
			"PG0793",
			"PG0794",
			"PG0795",
			"PG0796",
			"PG0797",
			"PG0798",
			"PG0799",
			"PG0800",
			"PG0801",
			"PG0802",
			"PG0803",
			"PG0804",
			"PG0805",
			"PG0806",
			"PG0807",
			"PG0808",
			"PG0809",
			"PG0810",
			"PG0811",
			"PG0812",
			"PG0813",
			"PG0814",
			"PG0815",
			"PG0816",
			"PG0817",
			"PG0818",
			"PG0819",
			"PG0820",
			"PG0821",
			"PG0822",
			"PG0823",
			"PG0824",
			"PG0825",
			"PG0826",
			"PG0827",
			"PG0828",
			"PG0829",
			"PG0830",
			"PG0831",
			"PG0832",
			"PG0833",
			"PG0834",
			"PG0835",
			"PG0836",
			"PG0837",
			"PG0838",
			"PG0839",
			"PG0840",
			"PG0841",
			"PG0842",
			"PG0843",
			"PG0844",
			"PG0845",
			"PG0846",
			"PG0847",
			"PG0848",
			"PG0849",
			"PG0850",
			"PG0851",
			"PG0852",
			"PG0853",
			"PG0854",
			"PG0855",
			"PG0856",
			"PG0857",
			"PG0858",
			"PG0859",
			"PG0860",
			"PG0861",
			"PG0862",
			"PG0863",
			"PG0864",
			"PG0865",
			"PG0866",
			"PG0867",
			"PG0868",
			"PG0869",
			"PG0870",
			"PG0871",
			"PG0872",
			"PG0873",
			"PG0874",
			"PG0875",
			"PG0876",
			"PG0877",
			"PG0878",
			"PG0879",
			"PG0880",
			"PG0881",
			"PG0882",
			"PG0883",
			"PG0884",
			"PG0885",
			"PG0886",
			"PG0887",
			"PG0888",
			"PG0889",
			"PG0890",
			"PG0891",
			"PG0893",
			"PG0894",
			"PG0895",
			"PG0896",
			"PG0897",
			"PG0898",
			"PG0899",
			"PG0900",
			"PG0901",
			"PG0902",
			"PG0903",
			"PG0904",
			"PG0905",
			"PG0906",
			"PG0907",
			"PG0908",
			"PG0909",
			"PG0910",
			"PG0911",
			"PG0912",
			"PG0913",
			"PG0914",
			"PG0915",
			"PG0916",
			"PG0917",
			"PG0918",
			"PG0919",
			"PG0920",
			"PG0921",
			"PG0922",
			"PG0923",
			"PG0924",
			"PG0925",
			"PG0926",
			"PG0927",
			"PG0928",
			"PG0929",
			"PG0930",
			"PG0931",
			"PG0932",
			"PG0933",
			"PG0934",
			"PG0935",
			"PG0936",
			"PG0937",
			"PG0938",
			"PG0939",
			"PG0940",
			"PG0941",
			"PG0942",
			"PG0943",
			"PG0944",
			"PG0945",
			"PG0946",
			"PG0947",
			"PG0948",
			"PG0949",
			"PG0950",
			"PG0951",
			"PG0952",
			"PG0953",
			"PG0954",
			"PG0955",
			"PG0956",
			"PG0957",
			"PG0958",
			"PG0959",
			"PG0960",
			"PG0961",
			"PG0962",
			"PG0963",
			"PG0964",
			"PG0965",
			"PG0966",
			"PG0967",
			"PG0968",
			"PG0969",
			"PG0970",
			"PG0971",
			"PG0972",
			"PG0973",
			"PG0974",
			"PG0975",
			"PG0976",
			"PG0977",
			"PG0978",
			"PG0979",
			"PG0980",
			"PG0981",
			"PG0982",
			"PG0983",
			"PG0984",
			"PG0985",
			"PG0986",
			"PG0987",
			"PG0988",
			"PG0989",
			"PG0990",
			"PG0991",
			"PG0992",
			"PG0993",
			"PG0994",
			"PG0995",
			"PG0996",
			"PG0997",
			"PG0998",
			"PG0999",
			"PG1000",
			"PG1001",
			"PG1002",
			"PG1004",
			"PG1005",
			"PG1006",
			"PG1007",
			"PG1008",
			"PG1009",
			"PG1010",
			"PG1011",
			"PG1012",
			"PG1013",
			"PG1014",
			"PG1015",
			"PG1016",
			"PG1017",
			"PG1018",
			"PG1019",
			"PG1020",
			"PG1021",
			"PG1022",
			"PG1023",
			"PG1024",
			"PG1025",
			"PG1026",
			"PG1027",
			"PG1028",
			"PG1029",
			"PG1030",
			"PG1031",
			"PG1032",
			"PG1033",
			"PG1034",
			"PG1035",
			"PG1036",
			"PG1037",
			"PG1038",
			"PG1039",
			"PG1040",
			"PG1041",
			"PG1042",
			"PG1043",
			"PG1044",
			"PG1045",
			"PG1046",
			"PG1047",
			"PG1048",
			"PG1049",
			"PG1050",
			"PG1051",
			"PG1052",
			"PG1053",
			"PG1054",
			"PG1055",
			"PG1056",
			"PG1057",
			"PG1058",
			"PG1059",
			"PG1060",
			"PG1061",
			"PG1062",
			"PG1063",
			"PG1064",
			"PG1065",
			"PG1066",
			"PG1067",
			"PG1068",
			"PG1069",
			"PG1070",
			"PG1071",
			"PG1072",
			"PG1073",
			"PG1074",
			"PG1075",
			"PG1076",
			"PG1077",
			"PG1078",
			"PG1079",
			"PG1080",
			"PG1081",
			"PG1082",
			"PG1083",
			"PG1084",
			"PG1085",
			"PG1086",
			"PG1087",
			"PG1088",
			"PG1089",
			"PG1090",
			"PG1091",
			"PG1092",
			"PG1093",
			"PG1094",
			"PG1095",
			"PG1096",
			"PG1097",
			"PG1098",
			"PG1099",
			"PG1100",
			"PG1101",
			"PG1102",
			"PG1103",
			"PG1104",
			"PG1105",
			"PG1106",
			"PG1107",
			"PG1108",
			"PG1109",
			"PG1110",
			"PG1111",
			"PG1112",
			"PG1113",
			"PG1115",
			"PG1116",
			"PG1117",
			"PG1118",
			"PG1119",
			"PG1120",
			"PG1121",
			"PG1122",
			"PG1123",
			"PG1124",
			"PG1125",
			"PG1126",
			"PG1127",
			"PG1128",
			"PG1129",
			"PG1130",
			"PG1131",
			"PG1132",
			"PG1133",
			"PG1134",
			"PG1135",
			"PG1136",
			"PG1137",
			"PG1138",
			"PG1139",
			"PG1140",
			"PG1141",
			"PG1142",
			"PG1143",
			"PG1144",
			"PG1145",
			"PG1146",
			"PG1147",
			"PG1148",
			"PG1149",
			"PG1150",
			"PG1151",
			"PG1152",
			"PG1153",
			"PG1154",
			"PG1155",
			"PG1156",
			"PG1157",
			"PG1158",
			"PG1159",
			"PG1160",
			"PG1161",
			"PG1162",
			"PG1163",
			"PG1164",
			"PG1165",
			"PG1166",
			"PG1167",
			"PG1168",
			"PG1169",
			"PG1170",
			"PG1171",
			"PG1172",
			"PG1173",
			"PG1174",
			"PG1175",
			"PG1176",
			"PG1177",
			"PG1178",
			"PG1179",
			"PG1180",
			"PG1181",
			"PG1182",
			"PG1183",
			"PG1184",
			"PG1185",
			"PG1186",
			"PG1187",
			"PG1188",
			"PG1189",
			"PG1190",
			"PG1191",
			"PG1192",
			"PG1193",
			"PG1194",
			"PG1195",
			"PG1196",
			"PG1197",
			"PG1198",
			"PG1199",
			"PG1200",
			"PG1201",
			"PG1202",
			"PG1203",
			"PG1204",
			"PG1205",
			"PG1206",
			"PG1207",
			"PG1208",
			"PG1209",
			"PG1210",
			"PG1211",
			"PG1212",
			"PG1213",
			"PG1214",
			"PG1215",
			"PG1216",
			"PG1217",
			"PG1218",
			"PG1219",
			"PG1220",
			"PG1221",
			"PG1222",
			"PG1223",
			"PG1224",
			"PG1226",
			"PG1227",
			"PG1228",
			"PG1229",
			"PG1230",
			"PG1231",
			"PG1232",
			"PG1233",
			"PG1234",
			"PG1235",
			"PG1236",
			"PG1237",
			"PG1238",
			"PG1239",
			"PG1240",
			"PG1241",
			"PG1242",
			"PG1243",
			"PG1244",
			"PG1245",
			"PG1246",
			"PG1247",
			"PG1248",
			"PG1249",
			"PG1250",
			"PG1251",
			"PG1252",
			"PG1253",
			"PG1254",
			"PG1255",
			"PG1256",
			"PG1257",
			"PG1258",
			"PG1259",
			"PG1260",
			"PG1261",
			"PG1262",
			"PG1263",
			"PG1264",
			"PG1265",
			"PG1266",
			"PG1267",
			"PG1268",
			"PG1269",
			"PG1270",
			"PG1271",
			"PG1272",
			"PG1273",
			"PG1274",
			"PG1275",
			"PG1276",
			"PG1277",
			"PG1278",
			"PG1279",
			"PG1280",
			"PG1281",
			"PG1282",
			"PG1283",
			"PG1284",
			"PG1285",
			"PG1286",
			"PG1287",
			"PG1288",
			"PG1289",
			"PG1290",
			"PG1291",
			"PG1292",
			"PG1293",
			"PG1294",
			"PG1295",
			"PG1296",
			"PG1297",
			"PG1298",
			"PG1299",
			"PG1300",
			"PG1301",
			"PG1302",
			"PG1303",
			"PG1304",
			"PG1305",
			"PG1306",
			"PG1307",
			"PG1308",
			"PG1309",
			"PG1310",
			"PG1311",
			"PG1312",
			"PG1313",
			"PG1314",
			"PG1315",
			"PG1316",
			"PG1317",
			"PG1318",
			"PG1319",
			"PG1320",
			"PG1321",
			"PG1322",
			"PG1323",
			"PG1324",
			"PG1325",
			"PG1326",
			"PG1327",
			"PG1328",
			"PG1329",
			"PG1330",
			"PG1331",
			"PG1332",
			"PG1333",
			"PG1334",
			"PG1335",
			"PG1337",
			"PG1338",
			"PG1339",
			"PG1340",
			"PG1341",
			"PG1342",
			"PG1343",
			"PG1344",
			"PG1345",
			"PG1346",
			"PG1347",
			"PG1348",
			"PG1349",
			"PG1350",
			"PG1351",
			"PG1352",
			"PG1353",
			"PG1354",
			"PG1355",
			"PG1356",
			"PG1357",
			"PG1358",
			"PG1359",
			"PG1360",
			"PG1361",
			"PG1362",
			"PG1363",
			"PG1364",
			"PG1365",
			"PG1366",
			"PG1367",
			"PG1368",
			"PG1369",
			"PG1370",
			"PG1371",
			"PG1372",
			"PG1373",
			"PG1374",
			"PG1375",
			"PG1376",
			"PG1377",
			"PG1378",
			"PG1379",
			"PG1380",
			"PG1381",
			"PG1382",
			"PG1383",
			"PG1384",
			"PG1385",
			"PG1386",
			"PG1387",
			"PG1388",
			"PG1389",
			"PG1390",
			"PG1391",
			"PG1392",
			"PG1393",
			"PG1394",
			"PG1395",
			"PG1396",
			"PG1397",
			"PG1398",
			"PG1399",
			"PG1400",
			"PG1401",
			"PG1402",
			"PG1403",
			"PG1404",
			"PG1405",
			"PG1406",
			"PG1407",
			"PG1408",
			"PG1409",
			"PG1410",
			"PG1411",
			"PG1412",
			"PG1413",
			"PG1414",
			"PG1415",
			"PG1416",
			"PG1417",
			"PG1418",
			"PG1419",
			"PG1420",
			"PG1421",
			"PG1422",
			"PG1423",
			"PG1424",
			"PG1425",
			"PG1426",
			"PG1427",
			"PG1428",
			"PG1429",
			"PG1430",
			"PG1431",
			"PG1432",
			"PG1433",
			"PG1434",
			"PG1435",
			"PG1436",
			"PG1437",
			"PG1438",
			"PG1439",
			"PG1440",
			"PG1441",
			"PG1442",
			"PG1443",
			"PG1444",
			"PG1445",
			"PG1446",
			"PG1448",
			"PG1449",
			"PG1450",
			"PG1451",
			"PG1452",
			"PG1453",
			"PG1454",
			"PG1455",
			"PG1456",
			"PG1457",
			"PG1458",
			"PG1459",
			"PG1460",
			"PG1461",
			"PG1462",
			"PG1463",
			"PG1464",
			"PG1465",
			"PG1466",
			"PG1467",
			"PG1468",
			"PG1469",
			"PG1470",
			"PG1471",
			"PG1472",
			"PG1473",
			"PG1474",
			"PG1475",
			"PG1476",
			"PG1477",
			"PG1478",
			"PG1479",
			"PG1480",
			"PG1481",
			"PG1482",
			"PG1483",
			"PG1484",
			"PG1485",
			"PG1486",
			"PG1487",
			"PG1488",
			"PG1489",
			"PG1490",
			"PG1491",
			"PG1492",
			"PG1493",
			"PG1494",
			"PG1495",
			"PG1496",
			"PG1497",
			"PG1498",
			"PG1499",
			"PG1500",
			"PG1501",
			"PG1502",
			"PG1503",
			"PG1504",
			"PG1505",
			"PG1506",
			"PG1507",
			"PG1508",
			"PG1509",
			"PG1510",
			"PG1511",
			"PG1512",
			"PG1513",
			"PG1514",
			"PG1515",
			"PG1516",
			"PG1517",
			"PG1518",
			"PG1519",
			"PG1520",
			"PG1521",
			"PG1522",
			"PG1523",
			"PG1524",
			"PG1525",
			"PG1526",
			"PG1527",
			"PG1528",
			"PG1529",
			"PG1530",
			"PG1531",
			"PG1532",
			"PG1533",
			"PG1534",
			"PG1535",
			"PG1536",
			"PG1537",
			"PG1538",
			"PG1539",
			"PG1540",
			"PG1541",
			"PG1542",
			"PG1543",
			"PG1544",
			"PG1545",
			"PG1546",
			"PG1547",
			"PG1548",
			"PG1549",
			"PG1550",
			"PG1551",
			"PG1552",
			"PG1553",
			"PG1554",
			"PG1555",
			"PG1556",
			"PG1557",
			"PG1558",
			"PG1559",
			"PG1560",
			"PG1561",
			"PG1562");

	public static void main(String[] args) {
		for (int i = 34; i < 519; i++) {
			String bookID = "BK" + StringUtil.zeroUme(i + "", 4);
			int n = Random.d10(1) + 1;
			Set<String> pageIDs = new HashSet<>();
			for (int j = 0; j < n; j++) {
				String pageID = Random.random(l);
				pageIDs.add(pageID);
			}
			for (var v : pageIDs) {
				System.out.println(bookID + "\t" + v);
			}
		}
//		new Test().gameStart();

	}

	private Test() {
		super(GameOption.defaultOption());
	}

	@Override
	protected void startUp() {
	}

	@Override
	protected void dispose() {
	}

	@Override
	protected void update(GameTimeManager gtm, InputState is) {
	}

	@Override
	protected void draw(GraphicsContext gc) {
	}

}
