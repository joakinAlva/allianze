import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'tcperfil',
        data: { pageTitle: 'apicotizadorApp.tCPERFIL.home.title' },
        loadChildren: () => import('./tcperfil/tcperfil.module').then(m => m.TCPERFILModule),
      },
      {
        path: 'tmusuario',
        data: { pageTitle: 'apicotizadorApp.tMUSUARIO.home.title' },
        loadChildren: () => import('./tmusuario/tmusuario.module').then(m => m.TMUSUARIOModule),
      },
      {
        path: 'tcunidadnegocio',
        data: { pageTitle: 'apicotizadorApp.tCUNIDADNEGOCIO.home.title' },
        loadChildren: () => import('./tcunidadnegocio/tcunidadnegocio.module').then(m => m.TCUNIDADNEGOCIOModule),
      },
      {
        path: 'tcejecutivo',
        data: { pageTitle: 'apicotizadorApp.tCEJECUTIVO.home.title' },
        loadChildren: () => import('./tcejecutivo/tcejecutivo.module').then(m => m.TCEJECUTIVOModule),
      },
      {
        path: 'tcsubgrupo',
        data: { pageTitle: 'apicotizadorApp.tCSUBGRUPO.home.title' },
        loadChildren: () => import('./tcsubgrupo/tcsubgrupo.module').then(m => m.TCSUBGRUPOModule),
      },
      {
        path: 'tctiporegla',
        data: { pageTitle: 'apicotizadorApp.tCTIPOREGLA.home.title' },
        loadChildren: () => import('./tctiporegla/tctiporegla.module').then(m => m.TCTIPOREGLAModule),
      },
      {
        path: 'tctipo',
        data: { pageTitle: 'apicotizadorApp.tCTIPO.home.title' },
        loadChildren: () => import('./tctipo/tctipo.module').then(m => m.TCTIPOModule),
      },
      {
        path: 'tcocupacion',
        data: { pageTitle: 'apicotizadorApp.tCOCUPACION.home.title' },
        loadChildren: () => import('./tcocupacion/tcocupacion.module').then(m => m.TCOCUPACIONModule),
      },
      {
        path: 'tcdescuentotiporiesgo',
        data: { pageTitle: 'apicotizadorApp.tCDESCUENTOTIPORIESGO.home.title' },
        loadChildren: () => import('./tcdescuentotiporiesgo/tcdescuentotiporiesgo.module').then(m => m.TCDESCUENTOTIPORIESGOModule),
      },
      {
        path: 'tcprimanetasdesc',
        data: { pageTitle: 'apicotizadorApp.tCPRIMANETASDESC.home.title' },
        loadChildren: () => import('./tcprimanetasdesc/tcprimanetasdesc.module').then(m => m.TCPRIMANETASDESCModule),
      },
      {
        path: 'tcfactorsami',
        data: { pageTitle: 'apicotizadorApp.tCFACTORSAMI.home.title' },
        loadChildren: () => import('./tcfactorsami/tcfactorsami.module').then(m => m.TCFACTORSAMIModule),
      },
      {
        path: 'tcdescuentosumaasegurada',
        data: { pageTitle: 'apicotizadorApp.tCDESCUENTOSUMAASEGURADA.home.title' },
        loadChildren: () =>
          import('./tcdescuentosumaasegurada/tcdescuentosumaasegurada.module').then(m => m.TCDESCUENTOSUMAASEGURADAModule),
      },
      {
        path: 'tcprimatarifa',
        data: { pageTitle: 'apicotizadorApp.tCPRIMATARIFA.home.title' },
        loadChildren: () => import('./tcprimatarifa/tcprimatarifa.module').then(m => m.TCPRIMATARIFAModule),
      },
      {
        path: 'tcrecargopagofraccionado',
        data: { pageTitle: 'apicotizadorApp.tCRECARGOPAGOFRACCIONADO.home.title' },
        loadChildren: () =>
          import('./tcrecargopagofraccionado/tcrecargopagofraccionado.module').then(m => m.TCRECARGOPAGOFRACCIONADOModule),
      },
      {
        path: 'tccobertura',
        data: { pageTitle: 'apicotizadorApp.tCCOBERTURA.home.title' },
        loadChildren: () => import('./tccobertura/tccobertura.module').then(m => m.TCCOBERTURAModule),
      },
      {
        path: 'tcconcepto',
        data: { pageTitle: 'apicotizadorApp.tCCONCEPTO.home.title' },
        loadChildren: () => import('./tcconcepto/tcconcepto.module').then(m => m.TCCONCEPTOModule),
      },
      {
        path: 'tchipotesis',
        data: { pageTitle: 'apicotizadorApp.tCHIPOTESIS.home.title' },
        loadChildren: () => import('./tchipotesis/tchipotesis.module').then(m => m.TCHIPOTESISModule),
      },
      {
        path: 'tccoeficiente',
        data: { pageTitle: 'apicotizadorApp.tCCOEFICIENTE.home.title' },
        loadChildren: () => import('./tccoeficiente/tccoeficiente.module').then(m => m.TCCOEFICIENTEModule),
      },
      {
        path: 'tcfactordescuento',
        data: { pageTitle: 'apicotizadorApp.tCFACTORDESCUENTO.home.title' },
        loadChildren: () => import('./tcfactordescuento/tcfactordescuento.module').then(m => m.TCFACTORDESCUENTOModule),
      },
      {
        path: 'tcsumaasegurada',
        data: { pageTitle: 'apicotizadorApp.tCSUMAASEGURADA.home.title' },
        loadChildren: () => import('./tcsumaasegurada/tcsumaasegurada.module').then(m => m.TCSUMAASEGURADAModule),
      },
      {
        path: 'tcrangoprima',
        data: { pageTitle: 'apicotizadorApp.tCRANGOPRIMA.home.title' },
        loadChildren: () => import('./tcrangoprima/tcrangoprima.module').then(m => m.TCRANGOPRIMAModule),
      },
      {
        path: 'tctipoproducto',
        data: { pageTitle: 'apicotizadorApp.tCTIPOPRODUCTO.home.title' },
        loadChildren: () => import('./tctipoproducto/tctipoproducto.module').then(m => m.TCTIPOPRODUCTOModule),
      },
      {
        path: 'tcrefencia',
        data: { pageTitle: 'apicotizadorApp.tCREFENCIA.home.title' },
        loadChildren: () => import('./tcrefencia/tcrefencia.module').then(m => m.TCREFENCIAModule),
      },
      {
        path: 'tccuotariesgo',
        data: { pageTitle: 'apicotizadorApp.tCCUOTARIESGO.home.title' },
        loadChildren: () => import('./tccuotariesgo/tccuotariesgo.module').then(m => m.TCCUOTARIESGOModule),
      },
      {
        path: 'tccuotavalor',
        data: { pageTitle: 'apicotizadorApp.tCCUOTAVALOR.home.title' },
        loadChildren: () => import('./tccuotavalor/tccuotavalor.module').then(m => m.TCCUOTAVALORModule),
      },
      {
        path: 'tccuotatarifasdesc',
        data: { pageTitle: 'apicotizadorApp.tCCUOTATARIFASDESC.home.title' },
        loadChildren: () => import('./tccuotatarifasdesc/tccuotatarifasdesc.module').then(m => m.TCCUOTATARIFASDESCModule),
      },
      {
        path: 'tccuotapropuesta',
        data: { pageTitle: 'apicotizadorApp.tCCUOTAPROPUESTA.home.title' },
        loadChildren: () => import('./tccuotapropuesta/tccuotapropuesta.module').then(m => m.TCCUOTAPROPUESTAModule),
      },
      {
        path: 'tcedadrecargo',
        data: { pageTitle: 'apicotizadorApp.tCEDADRECARGO.home.title' },
        loadChildren: () => import('./tcedadrecargo/tcedadrecargo.module').then(m => m.TCEDADRECARGOModule),
      },
      {
        path: 'tccovid',
        data: { pageTitle: 'apicotizadorApp.tCCOVID.home.title' },
        loadChildren: () => import('./tccovid/tccovid.module').then(m => m.TCCOVIDModule),
      },
      {
        path: 'tcregional',
        data: { pageTitle: 'apicotizadorApp.tCREGIONAL.home.title' },
        loadChildren: () => import('./tcregional/tcregional.module').then(m => m.TCREGIONALModule),
      },
      {
        path: 'tccovidtarifas',
        data: { pageTitle: 'apicotizadorApp.tCCOVIDTARIFAS.home.title' },
        loadChildren: () => import('./tccovidtarifas/tccovidtarifas.module').then(m => m.TCCOVIDTARIFASModule),
      },
      {
        path: 'tcestatuscotizacion',
        data: { pageTitle: 'apicotizadorApp.tCESTATUSCOTIZACION.home.title' },
        loadChildren: () => import('./tcestatuscotizacion/tcestatuscotizacion.module').then(m => m.TCESTATUSCOTIZACIONModule),
      },
      {
        path: 'tmcotizacion',
        data: { pageTitle: 'apicotizadorApp.tMCOTIZACION.home.title' },
        loadChildren: () => import('./tmcotizacion/tmcotizacion.module').then(m => m.TMCOTIZACIONModule),
      },
      {
        path: 'tmcotizacioninfo',
        data: { pageTitle: 'apicotizadorApp.tMCOTIZACIONINFO.home.title' },
        loadChildren: () => import('./tmcotizacioninfo/tmcotizacioninfo.module').then(m => m.TMCOTIZACIONINFOModule),
      },
      {
        path: 'tmcotizacionexppropia',
        data: { pageTitle: 'apicotizadorApp.tMCOTIZACIONEXPPROPIA.home.title' },
        loadChildren: () => import('./tmcotizacionexppropia/tmcotizacionexppropia.module').then(m => m.TMCOTIZACIONEXPPROPIAModule),
      },
      {
        path: 'tmasegurado',
        data: { pageTitle: 'apicotizadorApp.tMASEGURADO.home.title' },
        loadChildren: () => import('./tmasegurado/tmasegurado.module').then(m => m.TMASEGURADOModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
