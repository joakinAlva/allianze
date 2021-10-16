export interface ITCCOVIDTARIFAS {
  idCovidTarifas?: number;
  edad?: string;
  qxcnsfg?: number;
  titular?: string;
  conyuge?: string;
  hijoMayor?: string;
  hijoMenor?: string;
  qxTitular?: number;
  qxConyuge?: number;
  qxHijoMayor?: number;
  qxHijoMenor?: number;
  qxTitularRecargada?: number;
  qxConyugeRecargada?: number;
  qxHijoMayorRecargada?: number;
  qxHijoMenorRecargada?: number;
  valorGff?: number;
  valorGffCovid?: number;
}

export class TCCOVIDTARIFAS implements ITCCOVIDTARIFAS {
  constructor(
    public idCovidTarifas?: number,
    public edad?: string,
    public qxcnsfg?: number,
    public titular?: string,
    public conyuge?: string,
    public hijoMayor?: string,
    public hijoMenor?: string,
    public qxTitular?: number,
    public qxConyuge?: number,
    public qxHijoMayor?: number,
    public qxHijoMenor?: number,
    public qxTitularRecargada?: number,
    public qxConyugeRecargada?: number,
    public qxHijoMayorRecargada?: number,
    public qxHijoMenorRecargada?: number,
    public valorGff?: number,
    public valorGffCovid?: number
  ) {}
}

export function getTCCOVIDTARIFASIdentifier(tCCOVIDTARIFAS: ITCCOVIDTARIFAS): number | undefined {
  return tCCOVIDTARIFAS.idCovidTarifas;
}
