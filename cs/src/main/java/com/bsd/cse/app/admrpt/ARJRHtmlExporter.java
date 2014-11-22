/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.admrpt;

import java.awt.Color;
import java.io.IOException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.JRLine;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintEllipse;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRPrintLine;
import net.sf.jasperreports.engine.JRPrintRectangle;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.export.CutsInfo;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRExporterGridCell;
import net.sf.jasperreports.engine.export.JRGridLayout;
import net.sf.jasperreports.engine.util.JRColorUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author arg
 */
public class ARJRHtmlExporter extends JRHtmlExporter{
    private static Log log = LogFactory.getLog(ARJRHtmlExporter.class);
    private Boolean isFirefoxBrowser;

    public ARJRHtmlExporter(Boolean isFirefoxBrowser)
    {
        super();
        this.isFirefoxBrowser = isFirefoxBrowser;        
    }

    @Override
    protected void exportGrid(JRGridLayout gridLayout, boolean whitePageBackground) throws IOException, JRException
      {
            CutsInfo xCuts = gridLayout.getXCuts();
            JRExporterGridCell[][] grid = gridLayout.getGrid();

            String tableStyle = "width: " + gridLayout.getWidth() + sizeUnit + (this.isFirefoxBrowser?"":"; border-collapse: collapse");
            String additionalTableStyle = emptyCellStringProvider.getReportTableStyle();
            if (additionalTableStyle != null)
            {
                  tableStyle += "; " + additionalTableStyle;
            }

            writer.write("<table style=\"" + tableStyle + "\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"");
            if (whitePageBackground)
            {
                  writer.write(" bgcolor=\"white\"");
            }
            writer.write(">\n");

            if (whitePageBackground)
            {
                  setBackcolor(Color.white);
            }

            writer.write("<tr>\n");
            int width = 0;
            for(int i = 1; i < xCuts.size(); i++)
            {
                  width = xCuts.getCut(i) - xCuts.getCut(i - 1);
                  writer.write("  <td" + emptyCellStringProvider.getStringForCollapsedTD(imagesURI, width, 1, sizeUnit) + "</td>\n");
            }
            writer.write("</tr>\n");

            for(int y = 0; y < grid.length; y++)
            {
                  if (gridLayout.getYCuts().isCutSpanned(y) || !isRemoveEmptySpace)
                  {
                        JRExporterGridCell[] gridRow = grid[y];

                        int rowHeight = JRGridLayout.getRowHeight(gridRow);

                        boolean hasEmptyCell = hasEmptyCell(gridRow);

                        writer.write("<tr valign=\"top\"");
                        if (!hasEmptyCell)
                        {
                              writer.write(" style=\"height:" + rowHeight + sizeUnit + "\"");
                        }
                        writer.write(">\n");

                        for(int x = 0; x < gridRow.length; x++)
                        {
                              JRExporterGridCell gridCell = gridRow[x];
                              if(gridCell.getWrapper() == null)
                              {
                                    writeEmptyCell(gridCell, rowHeight);
                              }
                              else
                              {

                                    JRPrintElement element = gridCell.getWrapper().getElement();

                                    if (element instanceof JRPrintLine)
                                    {
                                          exportLine((JRPrintLine)element, gridCell);
                                    }
                                    else if (element instanceof JRPrintRectangle)
                                    {
                                          exportRectangle((JRPrintRectangle)element, gridCell);
                                    }
                                    else if (element instanceof JRPrintEllipse)
                                    {
                                          exportRectangle((JRPrintEllipse)element, gridCell);
                                    }
                                    else if (element instanceof JRPrintImage)
                                    {
                                          exportImage((JRPrintImage)element, gridCell);
                                    }
                                    else if (element instanceof JRPrintText)
                                    {
                                          exportText((JRPrintText)element, gridCell);
                                    }
                                    else if (element instanceof JRPrintFrame)
                                    {
                                          exportFrame((JRPrintFrame) element, gridCell);
                                    }
                                    else if (element instanceof JRGenericPrintElement)
                                    {
                                          exportGenericElement((JRGenericPrintElement) element,
                                                      gridCell, rowHeight);
                                    }
                              }

                              x += gridCell.getColSpan() - 1;
                        }

                        writer.write("</tr>\n");
                  }
            }

            if (whitePageBackground)
            {
                  restoreBackcolor();
            }

            writer.write("</table>\n");
      }


      private boolean hasEmptyCell(JRExporterGridCell[] gridRow)
      {
            if (gridRow[0].getWrapper() == null) // quick exit
            {
                  return true;
            }

            boolean hasEmptyCell = false;
            for(int x = 1; x < gridRow.length; x++)
            {
                  if (gridRow[x].getWrapper() == null)
                  {
                        hasEmptyCell = true;
                        break;
                  }
            }

            return hasEmptyCell;
      }




}
