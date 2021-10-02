export interface ITCPRIMANETASDESC {
  idPrimaNetaSdesc?: number;
  primaNetaSdesc?: string;
  margenMin?: number;
  margenMax?: number;
  maxComSd?: number;
  maxComEp?: number;
}

export class TCPRIMANETASDESC implements ITCPRIMANETASDESC {
  constructor(
    public idPrimaNetaSdesc?: number,
    public primaNetaSdesc?: string,
    public margenMin?: number,
    public margenMax?: number,
    public maxComSd?: number,
    public maxComEp?: number
  ) {}
}

export function getTCPRIMANETASDESCIdentifier(tCPRIMANETASDESC: ITCPRIMANETASDESC): number | undefined {
  return tCPRIMANETASDESC.idPrimaNetaSdesc;
}
