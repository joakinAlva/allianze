export interface ITCCUOTARIESGO {
  idCuotaRiesgo?: number;
  edad?: string;
  valorBa?: number;
  valorBaCovid?: number;
  valorBipTres?: number;
  valorBipSeis?: number;
  valorBit?: number;
  valorMa?: number;
  valorDi?: number;
  valorTi?: number;
  valorGff?: number;
  valorGffCovid?: number;
  valorCa?: number;
  valorGe?: number;
  valorIqUno?: number;
  valorIqDos?: number;
}

export class TCCUOTARIESGO implements ITCCUOTARIESGO {
  constructor(
    public idCuotaRiesgo?: number,
    public edad?: string,
    public valorBa?: number,
    public valorBaCovid?: number,
    public valorBipTres?: number,
    public valorBipSeis?: number,
    public valorBit?: number,
    public valorMa?: number,
    public valorDi?: number,
    public valorTi?: number,
    public valorGff?: number,
    public valorGffCovid?: number,
    public valorCa?: number,
    public valorGe?: number,
    public valorIqUno?: number,
    public valorIqDos?: number
  ) {}
}

export function getTCCUOTARIESGOIdentifier(tCCUOTARIESGO: ITCCUOTARIESGO): number | undefined {
  return tCCUOTARIESGO.idCuotaRiesgo;
}
