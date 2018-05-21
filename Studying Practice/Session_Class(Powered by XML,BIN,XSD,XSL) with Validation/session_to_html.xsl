<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:functions="com.session.CustomSAXParser">

    <xsl:output method="html"/>

    <xsl:template match="/">
        <html>
            <body style="font-family:Harlow Solid;font-size:25px;background-color:#EEEEEE;align:center">
                <h2>Session Results:</h2>
                <table style ="width:1000;align:center">
                    <tr bgcolor="#9acd32">
                        <th>Number</th>
                        <th>Surname</th>
                        <th>Subject</th>
                        <th>Mark</th>
                    </tr>
                    <xsl:for-each select="session/sessionData">
                        <tr style="background-color:teal;color:white;padding:4px;">
                            <td style="background-color:blue;font-size:25px;"><xsl:value-of select="//number/@name"/></td>
                            <td style="background-color:green;font-size:25px;"><xsl:value-of select="//surname/@name"/></td>
                            <td style="background-color:purple;font-size:25px;"><xsl:value-of select="//subject/@name"/></td>
                            <td style="background-color:red;font-size:25px;"><xsl:value-of select="//mark/@name"/></td>
                        </tr>
                    </xsl:for-each>
                    <tr style="background-color:dark-gray;color:white;padding:4px;">
                        <td style="background-color:gray;color:white;font-size:25px;">----</td>
                        <td style="background-color:gray;color:white;font-size:25px;">----</td>
                        <td style="background-color:gray;color:white;font-size:25px;">----</td>
                        <td style="background-color:gray;color:white;font-size:25px;">----</td>
                    </tr>
                    <tr style="background-color:dark-gray;color:white;padding:4px;">
                        <td style="background-color:black; color:white;font-size:25px;">Popular Subject:</td>
                        <td style="background-color:black;color:red;font-size:25px;"><xsl:value-of select="functions:getPopular()"/></td>
                        <td style="background-color:black;color:white;font-size:25px;">Average Mark:</td>
                        <td style="background-color:black;color:red;font-size:25px;"><xsl:value-of select="functions:getAverage()"/></td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>