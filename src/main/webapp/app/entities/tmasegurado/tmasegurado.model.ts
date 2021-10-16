import * as dayjs from 'dayjs';

export interface ITMASEGURADO {
  idAsegurados?: number;
  numero?: number;
  excedente?: number;
  subgrupo?: number;
  nombre?: string;
  fNacimiento?: dayjs.Dayjs;
  sueldo?: number;
  reglaMonto?: number;
  edad?: number;
  saTotal?: number;
  saTopado?: number;
  basica?: number;
  basicaCovid?: number;
  extrabas?: number;
  primaBasica?: number;
  invalidez?: number;
  extraInv?: number;
  exencion?: number;
  extraExe?: number;
  muerteAcc?: number;
  extraAcc?: number;
  valorGff?: number;
  valorGffCovid?: number;
  extraGff?: number;
  primaGff?: number;
  primaCa?: number;
  extraCa?: number;
  primaGe?: number;
  extraGe?: number;
  primaIqs?: number;
  extraIqa?: number;
  primaIqv?: number;
  extraIqv?: number;
}

export class TMASEGURADO implements ITMASEGURADO {
  constructor(
    public idAsegurados?: number,
    public numero?: number,
    public excedente?: number,
    public subgrupo?: number,
    public nombre?: string,
    public fNacimiento?: dayjs.Dayjs,
    public sueldo?: number,
    public reglaMonto?: number,
    public edad?: number,
    public saTotal?: number,
    public saTopado?: number,
    public basica?: number,
    public basicaCovid?: number,
    public extrabas?: number,
    public primaBasica?: number,
    public invalidez?: number,
    public extraInv?: number,
    public exencion?: number,
    public extraExe?: number,
    public muerteAcc?: number,
    public extraAcc?: number,
    public valorGff?: number,
    public valorGffCovid?: number,
    public extraGff?: number,
    public primaGff?: number,
    public primaCa?: number,
    public extraCa?: number,
    public primaGe?: number,
    public extraGe?: number,
    public primaIqs?: number,
    public extraIqa?: number,
    public primaIqv?: number,
    public extraIqv?: number
  ) {}
}

export function getTMASEGURADOIdentifier(tMASEGURADO: ITMASEGURADO): number | undefined {
  return tMASEGURADO.idAsegurados;
}
