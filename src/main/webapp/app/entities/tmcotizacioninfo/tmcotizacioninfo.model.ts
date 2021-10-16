import * as dayjs from 'dayjs';

export interface ITMCOTIZACIONINFO {
  idCotizacionInfo?: number;
  numero?: number;
  cotizacion?: number;
  region?: number;
  fechaSolicitud?: dayjs.Dayjs;
  responsable?: number;
  fechaEntrega?: dayjs.Dayjs;
  contratante?: number;
  tipoUno?: number;
  tipoDos?: number;
  poliza?: number;
  inicioVigencia?: dayjs.Dayjs;
  finVigencia?: dayjs.Dayjs;
  intermediario?: string;
  rfc?: string;
  ejecutivo?: number;
  suscriptor?: number;
  plan?: number;
  ocupacion?: number;
  primaCovidVida?: number;
  primaCovidGff?: number;
  descuentoPrimaCovid?: number;
  extraPrimaVida?: number;
  extraPrimaGff?: number;
  porcentajeExtraPrimaVida?: number;
  porcentajeExtraPrimaGff?: number;
  valorFtr?: number;
  sami?: number;
  samiMin?: number;
  samiMax?: number;
  margen?: number;
  margenMin?: number;
  margenMax?: number;
  comision?: number;
  comisionMin?: number;
  comisionMax?: number;
  descuento?: number;
  descuentoMin?: number;
  descuentoMax?: number;
  primaneta?: number;
  primaNetaMin?: number;
  primaNetaMax?: number;
  derechoPoliza?: number;
  derechoPolizaMin?: number;
  derechoPolizaMax?: number;
  mayores?: number;
  mayoresMin?: number;
  mayoresMax?: number;
  asegurados?: number;
  aseguradosMin?: number;
  aseguradosMax?: number;
  saPromedio?: number;
  saPromedioMin?: number;
  saPromedioMax?: number;
  varSa?: number;
  varSaMin?: number;
  varSaMax?: number;
  saTotal?: number;
  saMaxima?: number;
  saMaximaMin?: number;
  saMaximaMax?: number;
  subgrupos?: number;
  subgruposMin?: number;
  edadMediaMin?: number;
  edadActuarial?: number;
  edadActuarialMin?: number;
  edadActPond?: number;
  edadActPondMin?: number;
  edadMin?: number;
  edadMinDos?: number;
  edadMaxDos?: number;
  edadMaxTres?: number;
  coeficienteVariacion?: number;
  factorTrUnoGiro?: number;
  factorSaProm?: number;
  pNetaGlobalSdmc?: number;
  pNetaGlobalCdmcCuota?: number;
  pNetaGlobalSmccdesc?: number;
  pNetaGlobalSmccdescCuota?: number;
  pNetaBasicaSdmc?: number;
  pNetaBasicaSdmcCuota?: number;
  pNetaBasicaCdmc?: number;
  pNetaBasicaCdmcCuota?: number;
}

export class TMCOTIZACIONINFO implements ITMCOTIZACIONINFO {
  constructor(
    public idCotizacionInfo?: number,
    public numero?: number,
    public cotizacion?: number,
    public region?: number,
    public fechaSolicitud?: dayjs.Dayjs,
    public responsable?: number,
    public fechaEntrega?: dayjs.Dayjs,
    public contratante?: number,
    public tipoUno?: number,
    public tipoDos?: number,
    public poliza?: number,
    public inicioVigencia?: dayjs.Dayjs,
    public finVigencia?: dayjs.Dayjs,
    public intermediario?: string,
    public rfc?: string,
    public ejecutivo?: number,
    public suscriptor?: number,
    public plan?: number,
    public ocupacion?: number,
    public primaCovidVida?: number,
    public primaCovidGff?: number,
    public descuentoPrimaCovid?: number,
    public extraPrimaVida?: number,
    public extraPrimaGff?: number,
    public porcentajeExtraPrimaVida?: number,
    public porcentajeExtraPrimaGff?: number,
    public valorFtr?: number,
    public sami?: number,
    public samiMin?: number,
    public samiMax?: number,
    public margen?: number,
    public margenMin?: number,
    public margenMax?: number,
    public comision?: number,
    public comisionMin?: number,
    public comisionMax?: number,
    public descuento?: number,
    public descuentoMin?: number,
    public descuentoMax?: number,
    public primaneta?: number,
    public primaNetaMin?: number,
    public primaNetaMax?: number,
    public derechoPoliza?: number,
    public derechoPolizaMin?: number,
    public derechoPolizaMax?: number,
    public mayores?: number,
    public mayoresMin?: number,
    public mayoresMax?: number,
    public asegurados?: number,
    public aseguradosMin?: number,
    public aseguradosMax?: number,
    public saPromedio?: number,
    public saPromedioMin?: number,
    public saPromedioMax?: number,
    public varSa?: number,
    public varSaMin?: number,
    public varSaMax?: number,
    public saTotal?: number,
    public saMaxima?: number,
    public saMaximaMin?: number,
    public saMaximaMax?: number,
    public subgrupos?: number,
    public subgruposMin?: number,
    public edadMediaMin?: number,
    public edadActuarial?: number,
    public edadActuarialMin?: number,
    public edadActPond?: number,
    public edadActPondMin?: number,
    public edadMin?: number,
    public edadMinDos?: number,
    public edadMaxDos?: number,
    public edadMaxTres?: number,
    public coeficienteVariacion?: number,
    public factorTrUnoGiro?: number,
    public factorSaProm?: number,
    public pNetaGlobalSdmc?: number,
    public pNetaGlobalCdmcCuota?: number,
    public pNetaGlobalSmccdesc?: number,
    public pNetaGlobalSmccdescCuota?: number,
    public pNetaBasicaSdmc?: number,
    public pNetaBasicaSdmcCuota?: number,
    public pNetaBasicaCdmc?: number,
    public pNetaBasicaCdmcCuota?: number
  ) {}
}

export function getTMCOTIZACIONINFOIdentifier(tMCOTIZACIONINFO: ITMCOTIZACIONINFO): number | undefined {
  return tMCOTIZACIONINFO.idCotizacionInfo;
}
