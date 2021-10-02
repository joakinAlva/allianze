export interface ITCPRIMATARIFA {
  idPrimaTarifa?: number;
  divPrimaTarifa?: number;
  zNeta?: number;
  divPrimaRiesgo?: number;
  zRiesgo?: number;
}

export class TCPRIMATARIFA implements ITCPRIMATARIFA {
  constructor(
    public idPrimaTarifa?: number,
    public divPrimaTarifa?: number,
    public zNeta?: number,
    public divPrimaRiesgo?: number,
    public zRiesgo?: number
  ) {}
}

export function getTCPRIMATARIFAIdentifier(tCPRIMATARIFA: ITCPRIMATARIFA): number | undefined {
  return tCPRIMATARIFA.idPrimaTarifa;
}
